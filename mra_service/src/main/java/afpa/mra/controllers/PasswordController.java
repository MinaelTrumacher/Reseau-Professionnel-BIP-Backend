package afpa.mra.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import afpa.mra.entities.Utilisateur;
import afpa.mra.repositories.UtilisateurRepository;
import afpa.mra.services.PasswordService;

@RestController
@RequestMapping("/api/reset")
public class PasswordController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PasswordService passwordService;
    

    @PostMapping("/resetform")
    public ResponseEntity<Map<String, String>> sendResetEmail(@RequestParam("email") String email) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur.isPresent()) {
        System.out.println(utilisateur.get().getNom());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(utilisateur.get().getEmail());
            message.setSubject("Réinitialisation du mot de passe");
            Random random = new Random();
            int randomNumber = random.nextInt(999999) + 100000;
            passwordService.saveResetCode(utilisateur.get(), randomNumber);
            message.setText("Le code de réinitialisation du mot de passe est : " + randomNumber);
            mailSender.send(message);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Email evoyé");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Utilisateur introuvable");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
   }

    @PostMapping("/change")
    public ResponseEntity<?> resetpassword(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("code") Integer code ){
        String ckeckParam = passwordService.isCodeValid(code, email);
        switch (ckeckParam) {
            case "Utilisateur non trouvé":
                return ResponseEntity.badRequest().body("Utilisateur non trouvé");
            case "Code non trouvé":
                return ResponseEntity.badRequest().body("Code non trouvé");
            case "Code expired":
                return ResponseEntity.badRequest().body("Code expired");
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);
        String encryptedPassword = passwordEncoder.encode(password);
        Utilisateur user = utilisateur.get();
        user.setMdp(encryptedPassword);
        utilisateurRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("Password reset completed with success");
    }


}
