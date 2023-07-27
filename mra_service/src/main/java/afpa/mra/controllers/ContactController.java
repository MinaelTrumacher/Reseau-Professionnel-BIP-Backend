package afpa.mra.controllers;

import afpa.mra.entities.ContactForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;


    @PostMapping("/api/contact")
    public ResponseEntity<String> sendEmail(@RequestBody ContactForm form) {
        String recipientEmail = "afpa.bip@gmail.com";
        String subject = "Nouveau message de contact";
        String message = "Nom : " + form.getNom() + "\n"
                + "Prénom : " + form.getPrenom() + "\n"
                + "Email : " + form.getEmail() + "\n"
                + "Message : " + form.getMessage();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        try {
            mailSender.send(mailMessage);
            return ResponseEntity.ok("{\"message\": \"Mail sent successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error sending email\"}");
        }
    }

}
