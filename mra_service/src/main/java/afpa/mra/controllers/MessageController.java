package afpa.mra.controllers;


import afpa.mra.ExceptionPersonnalisee.UserNotFoundException;
import afpa.mra.entities.Message;
import afpa.mra.entities.MessageDto;
import afpa.mra.repositories.MessageRepository;
import afpa.mra.repositories.UtilisateurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static afpa.mra.entities.MessageDto.ConvertToMessageDto;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/messages")
public class MessageController {

    private final MessageRepository messageRepository;
    private final UtilisateurRepository utilisateurRepository;

    public MessageController(MessageRepository messageRepository, UtilisateurRepository utilisateurRepository) {
        this.messageRepository = messageRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping(path = "{expediteur_id}")
    public ResponseEntity<Object> getlastMessageOfAllConversations(@PathVariable Long expediteur_id) {

        List<Message> messages = messageRepository.findLastMessageSentOrRecevedByUser(expediteur_id);
        List<MessageDto> messageDtos = new ArrayList<>();
        messages.forEach(message -> {
            message.setDestinataire(utilisateurRepository.findById(message.getDestinataire().getId()).get());
            message.setExpediteur(utilisateurRepository.findById(message.getExpediteur().getId()).get());
            messageDtos.add(ConvertToMessageDto(message));
        });
        return new ResponseEntity<>(messageDtos, HttpStatus.OK);
    }

    @GetMapping(path = "{expediteur_id}/{destinataire_id}")
    public ResponseEntity<Object> getDetailsConversationBetween2User(@PathVariable Long expediteur_id, @PathVariable Long destinataire_id) {

        List<Message> messages = messageRepository.findByExpediteurIdAndDestinataireIdOrExpediteurIdAndDestinataireIdOrderByDateEnvoiAsc(
                expediteur_id,
                destinataire_id,
                destinataire_id,
                expediteur_id
        );

        List<MessageDto> messageDtos = new ArrayList<>();
        messages.forEach(message -> messageDtos.add(ConvertToMessageDto(message)));
        return new ResponseEntity<>(messageDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> CreateMessage(@RequestBody MessageDto messageDto) {

        try {
            Message message = messageDto.ConvertToMessage(utilisateurRepository);
            Message body = messageRepository.save(message);
            messageRepository.updateSupprimerParUserIdByExpediteurIdAndSupprimerParUserId(message.getDestinataire().getId(), message.getExpediteur().getId());
            MessageDto bodyMessageDto = ConvertToMessageDto(body);
            return new ResponseEntity<>(bodyMessageDto, HttpStatus.OK);
        } catch (UserNotFoundException e) {

            Map<String, Object> body = new HashMap<>();
            body.put("erreur", e.getMessage());
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateConversation(@RequestBody MessageDto messageDto) {

        messageRepository.updateVuToTrueForAllMessageWhereDestinataireIdAndExpediteurIdOrExpediteurIdAndDestinataireId(
                messageDto.getDestination_id(),
                messageDto.getExpediteur_id()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/message")
    public ResponseEntity<Object> deleteMessage(@RequestBody MessageDto messageDto) {

        Optional<Message> messageOptional = messageRepository.findById(messageDto.getId());

        if (messageOptional.isPresent()) {
            messageRepository.deleteById(messageDto.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/conversation/{userId}")
    public ResponseEntity<Object> deleteConversation(@RequestBody MessageDto messageDto, @PathVariable Long userId) {

        if (messageDto.getSupprimerParUserId() != null) {

            messageRepository.deleteByExpediteurIdAndDestinataireIdOrExpediteurIdAndDestinataireId(
                    messageDto.getExpediteur_id(),
                    messageDto.getDestination_id(),
                    messageDto.getDestination_id(),
                    messageDto.getExpediteur_id()
            );
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            messageRepository.updateSupprimerParUserIdByDestinataireIdAndExpediteurIdOrExpediteurIdAndDestinataireId(messageDto.getDestination_id(), messageDto.getExpediteur_id(), userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
