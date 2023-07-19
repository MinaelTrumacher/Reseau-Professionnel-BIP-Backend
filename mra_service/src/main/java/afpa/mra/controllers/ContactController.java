package afpa.mra.controllers;

import afpa.mra.entities.ContactForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;


    @PostMapping("/contact")
    public void sendEmail(@RequestBody ContactForm form) {
        // Récupérer les données du formulaire et les utiliser pour construire le contenu du mail
        String recipientEmail = "afpa.bip@gmail.com";
        String subject = "Nouveau message de contact";
        String message = "Nom : " + form.getNom() + "\n"
                + "Prénom : " + form.getPrenom() + "\n"
                + "Email : " + form.getEmail() + "\n"
                + "Message : " + form.getMessage();

        // Créer un objet SimpleMailMessage avec les détails du mail
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        // Envoyer le mail
        mailSender.send(mailMessage);
    }
}