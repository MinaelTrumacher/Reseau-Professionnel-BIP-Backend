package afpa.mra.services;

import afpa.mra.entities.Interaction;
import afpa.mra.entities.Publication;
import afpa.mra.entities.Utilisateur;
import afpa.mra.repositories.InteractionRepository;
import afpa.mra.repositories.PublicationRepository;
import afpa.mra.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InteractionService {

    private final InteractionRepository interactionRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PublicationRepository publicationRepository;

    @Autowired
    public InteractionService(InteractionRepository interactionRepository, UtilisateurRepository utilisateurRepository, PublicationRepository publicationRepository) {
        this.interactionRepository = interactionRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.publicationRepository = publicationRepository;
    }

    public Interaction createInteraction(Interaction interaction) {
        Utilisateur utilisateur = utilisateurRepository.getById(interaction.getUtilisateur().getId());
        Publication publication = publicationRepository.getById(interaction.getPublication().getId());

        interaction.setUtilisateur(utilisateur);
        interaction.setPublication(publication);

        return interactionRepository.save(interaction);
    }

    public Interaction getInteraction(Long id) {
        Optional<Interaction> optionalInteraction = interactionRepository.findById(id);
        return optionalInteraction.orElse(null);
    }

    public List<Interaction> getAllInteraction() {
        List<Interaction> interactionList = interactionRepository.findAll();
        return interactionList;
    }

    public Interaction updateInteraction(Interaction interaction) {
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
    public void deleteInteraction(Long id) {

        interactionRepository.deleteById(id);
    }
}
