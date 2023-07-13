package afpa.mra.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import afpa.mra.entities.Utilisateur;
import afpa.mra.repositories.UtilisateurRepository;

@RestController
@RequestMapping("api/reset")
public class PasswordController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @GetMapping("/{email}")
    public ResponseEntity<Map<String, String>> sendResetEmail(@PathVariable String email) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur.isPresent()) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(utilisateur.get().getEmail());
            message.setSubject("Réinitialisation du mot de passe");
            String resetUrl = "http://localhost:8080/api/reset/form/" + utilisateur.get().getId();
            message.setText("Veuillez cliquer sur le lien suivant pour réinitialiser votre mot de passe: " + resetUrl);
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

    @GetMapping("/form/{id}")
    public ModelAndView showResetPasswordForm(@PathVariable Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView("reset-password-form");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @PostMapping("/new")
    public ModelAndView resetPassword(@RequestParam("id") Long id, @RequestParam("password") String password) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        if (!utilisateur.isPresent()) {
            throw new IllegalStateException("Utilisateur pas trouvé.");
        }
        String encryptedPassword = passwordEncoder.encode(password);
        Utilisateur user = utilisateur.get();
        user.setMdp(encryptedPassword);
        utilisateurRepository.save(user);
        ModelAndView modelAndView = new ModelAndView("reset-password-success");
        modelAndView.addObject("message", "Le mot de passe a été réinitialisé avec succès");
        return modelAndView;
    }

}
