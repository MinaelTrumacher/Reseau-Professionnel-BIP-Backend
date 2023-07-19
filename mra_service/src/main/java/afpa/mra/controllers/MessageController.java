package afpa.mra.controllers;

import afpa.mra.entities.Message;
import afpa.mra.entities.MessageDto;
import afpa.mra.entities.Utilisateur;
import afpa.mra.repositories.MessageRepository;
import afpa.mra.repositories.UtilisateurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static afpa.mra.entities.MessageDto.ConvertToMessageDto;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/messages")
public class MessageController {

    private final MessageRepository messageRepository;
    private final UtilisateurRepository utilisateurRepository;

    public MessageController(MessageRepository messageRepository, UtilisateurRepository utilisateurRepository) {
        this.messageRepository = messageRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @PostMapping
    public ResponseEntity<Object> CreateMessage(@RequestBody Message message) {

        Optional<Utilisateur> optionalDestinataire = utilisateurRepository.findById(message.getDestinataire().getId());
        Optional<Utilisateur> optionalExpediteur = utilisateurRepository.findById(message.getExpediteur().getId());

        if (optionalDestinataire.isPresent() && optionalExpediteur.isPresent()) {
            message.setDestinataire(optionalDestinataire.get());
            message.setExpediteur(optionalExpediteur.get());
            messageRepository.save(message);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        Map<String, Object> body = new HashMap<>();
        if (optionalDestinataire.isEmpty()){
            body.put("erreur", "le destinataire n'a pas été trouvé !");
        }else {
            body.put("erreur", "l'expéditeur n'a pas été trouvé !");
        }
        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);

    }

    @GetMapping(path = "{expediteur_id}")
    public ResponseEntity<Object> getLastMessageOfAllChats(@PathVariable Long expediteur_id) {

        List<Message> messages = messageRepository.findLastMessagesWithUser(expediteur_id);
        List<MessageDto> messageDtos = new ArrayList<>();
        messages.forEach(message -> {
            message.setDestinataire(utilisateurRepository.findById(message.getDestinataire().getId()).get());
            message.setExpediteur(utilisateurRepository.findById(message.getExpediteur().getId()).get());
            messageDtos.add(ConvertToMessageDto(message));
        });
        return new ResponseEntity<>(messageDtos, HttpStatus.OK);
    }

    @GetMapping(path = "{expediteur_id}/{destinataire_id}")
    public ResponseEntity<Object> getAllMessagesOfChat(@PathVariable Long expediteur_id, @PathVariable Long destinataire_id) {

        List<Message> messages = messageRepository.findByExpediteurIdAndDestinataireIdOrExpediteurIdAndDestinataireIdOrderByDateEnvoiAsc(expediteur_id, destinataire_id, destinataire_id, expediteur_id);
        List<MessageDto> messageDtos = new ArrayList<>();
        messages.forEach(message -> messageDtos.add(ConvertToMessageDto(message)));
        return new ResponseEntity<>(messageDtos, HttpStatus.OK);
    }
}
