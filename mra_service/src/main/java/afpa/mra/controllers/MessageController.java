package afpa.mra.controllers;

import afpa.mra.entities.Message;
import afpa.mra.entities.Utilisateur;
import afpa.mra.repositories.UtilisateurRepository;
import afpa.mra.repositories.MessageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final EntityManagerFactory entityManagerFactory;
    private final UtilisateurRepository utilisateurRepository;
    private final MessageRepository messageRepository;
    public MessageController(EntityManagerFactory entityManagerFactory, UtilisateurRepository utilisateurRepository, MessageRepository messageRepository) {
        this.entityManagerFactory = entityManagerFactory;
        this.utilisateurRepository = utilisateurRepository;
        this.messageRepository = messageRepository;
    }

    @PostMapping
    public ResponseEntity<String> createMessage(@RequestBody Message message) {
        if(message.getContenu() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Votre message n'a pas de contenu.");
        }

        Utilisateur existingUtilisateur = utilisateurRepository.findById(message.getExpediteur().getId()).orElse(null);
        if(existingUtilisateur != null) {
            message.setExpediteur(existingUtilisateur);
        }

        Utilisateur existingUtilisateur1 = utilisateurRepository.findById(message.getDestinataire().getId()).orElse(null);
        if(existingUtilisateur1 != null) {
            message.setDestinataire(existingUtilisateur1);
        }

        Message createdMessage = messageRepository.save(message);
        if(createdMessage != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Le message a été crée.");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur a eu lieu lors de la création de votre message.");
        }

        /*try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(message);
                transaction.commit();
                return ResponseEntity.status(HttpStatus.CREATED).body("Votre message a bien été crée.");
            }
            catch (Exception e) {
                if(transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite durant la création de votre message.");
            }
        }*/
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            List<Message> messages = entityManager.createQuery("SELECT m from Message m", Message.class)
                    .getResultList();
            return ResponseEntity.ok(messages);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Message message = entityManager.find(Message.class, id);
            if(message != null) {
                return ResponseEntity.ok(message);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMessage(@PathVariable Long id, @RequestBody Message updatedMessage) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                Message message = entityManager.find(Message.class, id);
                if(message != null) {
                    message.setContenu(updatedMessage.getContenu());
                    entityManager.merge(message);
                    transaction.commit();
                    return ResponseEntity.ok("Le message a été mis à jour avec succès.");
                }
                else {
                    return ResponseEntity.notFound().build();
                }
            }
            catch (Exception e) {
                if(transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la mise à jour de votre message.");
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                Message message = entityManager.find(Message.class, id);
                if(message != null) {
                    entityManager.remove(message);
                    transaction.commit();
                    return ResponseEntity.ok("Le message a été supprimé avec succès.");
                }
                else {
                    return ResponseEntity.notFound().build();
                }
            }
            catch (Exception e) {
                if(transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression de votre message.");
            }
        }

    }
}
