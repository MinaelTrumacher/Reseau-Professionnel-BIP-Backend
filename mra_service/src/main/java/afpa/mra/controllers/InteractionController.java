package afpa.mra.controllers;

import afpa.mra.entities.Interaction;
import afpa.mra.entities.Publication;
import afpa.mra.entities.Utilisateur;
import afpa.mra.repositories.InteractionRepository;
import afpa.mra.repositories.PublicationRepository;
import afpa.mra.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/interactions")
public class InteractionController {

    @Autowired
    private InteractionRepository interactionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @PostMapping
    public Interaction createInteraction(@RequestBody Interaction interaction) {
        Utilisateur utilisateur = utilisateurRepository.getById(interaction.getUtilisateur().getId());
        Publication publication = publicationRepository.getById(interaction.getPublication().getId());

        interaction.setUtilisateur(utilisateur);
        interaction.setPublication(publication);

        return interactionRepository.save(interaction);
    }

    @GetMapping
    public List<Interaction> getAllInteraction() {
        List<Interaction> interactionList = interactionRepository.findAll();
        return interactionList;
    }

    @GetMapping(path = "{id}")
    public Interaction getInteraction(@PathVariable Long id) {
        Optional<Interaction> optionalInteraction = interactionRepository.findById(id);
        return optionalInteraction.orElse(null);
    }

    @PutMapping
    public Interaction updateInteraction(@RequestBody Interaction interaction) {
        Optional<Interaction> optionalInteraction = interactionRepository.findById(interaction.getId());
        Interaction existingInteraction = optionalInteraction.get();
        if (optionalInteraction.isPresent()) {
            existingInteraction.setTypeInteraction(interaction.getTypeInteraction());
            existingInteraction.setDateInteraction(interaction.getDateInteraction());

            Optional<Publication> optionalPublication = publicationRepository.findById(interaction.getPublication().getId());
            if (optionalPublication.isEmpty()) {
                Publication publication = publicationRepository.save(interaction.getPublication());
                existingInteraction.setPublication(publication);
            } else {
                existingInteraction.setPublication(optionalPublication.get());
            }
        }
        Interaction newInteraction = interactionRepository.save(existingInteraction);
        return new ResponseEntity<>(newInteraction, HttpStatus.OK).getBody();
    }

    @DeleteMapping(path = "{id}")
    public void deleteInteraction(@PathVariable Long id) {
        interactionRepository.deleteById(id);
    }
}
