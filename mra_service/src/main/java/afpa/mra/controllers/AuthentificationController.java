package afpa.mra.controllers;

import afpa.mra.entities.Geolocalisation;
import afpa.mra.entities.Utilisateur;
import afpa.mra.entities.VerificationToken;
import afpa.mra.repositories.GeolocalisationRepository;
import afpa.mra.repositories.UtilisateurRepository;
import afpa.mra.security.DecryptService;
import afpa.mra.security.TokenService;
import afpa.mra.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping("/api/authentification")
public class AuthentificationController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    public GeolocalisationRepository geolocalisationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DecryptService decryptService;



    @PostMapping("/login")
    public ResponseEntity<?> token(Authentication authentication) {
        Optional<Utilisateur> checkUtilisateur = utilisateurRepository.findByEmail(authentication.getName());
        if (checkUtilisateur.isPresent()) {
            Utilisateur utilisateur = checkUtilisateur.get();
            String etat = utilisateur.getEtatInscription();
            if ("autorisé".equals(etat)) {
                String token = tokenService.generateToken(authentication);
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("token", token);
                responseBody.put("userId", utilisateur.getId());
                responseBody.put("role", utilisateur.getRole());
                return ResponseEntity.ok(responseBody);
            } else {
                String errorMessage = "Échec de la connexion : utilisateur non vérifié.";
                return ResponseEntity.status(HttpStatus.LOCKED).body(errorMessage);
            }
        } else {
            String errorMessage = "Utilisateur pas trouvé.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> insert(@RequestBody Utilisateur user) {
        Optional<Utilisateur> utilisateurTrouve = utilisateurRepository.findByEmail(user.getEmail());
        if (utilisateurTrouve.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("L'utilisateur avec email " + user.getEmail() + " déjà existe");
        }
        String decryptedReceivedMdP = this.decryptService.decrypt(user.getMdp()); // decryptage du mdp envoye du front
        String encryptedPassword = passwordEncoder.encode(decryptedReceivedMdP);
        user.setMdp(encryptedPassword);
        user.setEtatInscription("vérif mail");
        Long geolocalisationId = user.getGeolocalisation().getId();
        Geolocalisation existingGeolocalisation = geolocalisationRepository.findById(geolocalisationId).orElse(null);
        if (existingGeolocalisation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Geolocalisation avec id " + geolocalisationId + " pas trouvée.");
        }
        user.setGeolocalisation(existingGeolocalisation);
        Utilisateur savedUser = utilisateurRepository.save(user);
        String validationToken = UUID.randomUUID().toString();
        verificationTokenService.saveVerificationToken(savedUser, validationToken);
        sendValidationEmail(savedUser.getEmail(), validationToken);
        Map<String, Object> customResponse = new HashMap<>();
        customResponse.put("message", "Utilisateur enregistré");
        customResponse.put("status", 200);
        return ResponseEntity.ok(customResponse);

    }


    private void sendValidationEmail(String email, String validationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Account Validation");
        message.setText("Veuillez cliquer sur le lien suivant pour valider votre compte: " +
                "http://localhost:8080/api/authentification/validation?token=" + validationToken);
        mailSender.send(message);
    }

    @GetMapping("/validation")
    public ModelAndView validateAccount(@RequestParam("token") String token) {
        VerificationToken verificationToken = verificationTokenService.getByToken(token);
        ModelAndView modelAndView = new ModelAndView();
        if (verificationToken != null) {
            if (isTokenExpired(verificationToken)) {
                modelAndView.addObject("message", "Le lien de vérification a expiré!");
                modelAndView.setViewName("verification-denied");
            } else {
                Utilisateur utilisateurVerifie = verificationToken.getUtilisateur();
                if (utilisateurVerifie != null) {
                    utilisateurVerifie.setEtatInscription("autorisé");
                    utilisateurRepository.save(utilisateurVerifie);
                    modelAndView.addObject("message", "Vous avez été vérifié avec succès. Vous pouvez maintenant vous connecter à votre compte!");
                    modelAndView.setViewName("verification-success");
                } else {
                    throw new IllegalStateException("Utilisateur object est null.");
                }
            }
        } else {
            throw new IllegalStateException("VerificationToken object est null.");
        }
        return modelAndView;
    }

    private boolean isTokenExpired(VerificationToken verificationToken) {
        Date expireDate = verificationToken.getExpireDate();
        Date currentDate = new Date();
        return expireDate != null && expireDate.before(currentDate);
    }

}
