package afpa.mra.controller;

import afpa.mra.configuration_token.ConfigurationToken;
import afpa.mra.entity.Message;
import afpa.mra.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class Controller {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    public ConfigurationToken configurationToken;

    @GetMapping("/conversation/{id}")
    public ResponseEntity<List<List<Message>>> getConversation(
            @PathVariable("id") Integer idUtilisateur,
            @RequestHeader(value = "Authorization", required = false) String authorization
    ) {
        List<Message> messages = messageRepository.findByUtilisateur(idUtilisateur);
        List<List<Message>> conversation = new ArrayList<>();
        Set<Integer> distinctIds = messages.stream()
                .flatMap(message -> Stream.of(message.getId().getIdEmetteur(), message.getId().getIdRecepteur()))
                .collect(Collectors.toSet());
        distinctIds.remove(idUtilisateur);
        distinctIds.forEach(id -> conversation.add(messageRepository.findByUtilisateurAndInterlocauteur(idUtilisateur, id)));

        return configurationToken.verifier(authorization, conversation);
    }

}
