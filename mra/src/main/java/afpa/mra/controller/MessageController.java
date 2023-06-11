package afpa.mra.controller;

import afpa.mra.configuration_token.ConfigurationToken;
import afpa.mra.entity.Message;
import afpa.mra.entity.MessageId;
import afpa.mra.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    public ConfigurationToken configurationToken;

    @GetMapping("/{idEmetteur}/{idRecepteur}/{idMessage}")
    public ResponseEntity<Message> getMessageById(
            @PathVariable("idEmetteur") int idEmetteur,
            @PathVariable("idRecepteur") int idRecepteur,
            @RequestHeader(value = "authorization", required = false) String authorization) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(
            @RequestBody Message message,
            @RequestHeader(value = "authorization", required = false) String authorization) {
        Message createdMessage = messageRepository.save(message);
        return configurationToken.verifier(authorization, createdMessage);
    }

    @PutMapping("/{idEmetteur}/{idRecepteur}/{idMessage}")
    public ResponseEntity<Message> updateMessage(@PathVariable("idEmetteur") int idEmetteur, @PathVariable("idRecepteur") int idRecepteur, @PathVariable("idMessage") int idMessage, @RequestBody Message message) {
        MessageId messageId = new MessageId(idEmetteur, idRecepteur, idMessage);
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            Message existingMessage = optionalMessage.get();
            existingMessage.setContenu(message.getContenu());
            existingMessage.setUrlImage(message.getUrlImage());

            Message updatedMessage = messageRepository.save(existingMessage);
            return ResponseEntity.ok(updatedMessage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idEmetteur}/{idRecepteur}/{idMessage}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("idEmetteur") int idEmetteur, @PathVariable("idRecepteur") int idRecepteur, @PathVariable("idMessage") int idMessage) {
        MessageId messageId = new MessageId(idEmetteur, idRecepteur, idMessage);
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            messageRepository.delete(optionalMessage.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


