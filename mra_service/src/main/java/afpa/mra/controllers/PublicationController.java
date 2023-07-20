package afpa.mra.controllers;

import afpa.mra.entities.Geolocalisation;
import afpa.mra.entities.Publication;
import afpa.mra.entities.Utilisateur;
import afpa.mra.repositories.GeolocalisationRepository;
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
@RequestMapping("/publications")
public class PublicationController {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private GeolocalisationRepository geolocalisationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private InteractionRepository interactionRepository;

    @PostMapping
    public Publication createPublication(@RequestBody Publication publication) {
        Geolocalisation geolocalisation = geolocalisationRepository.getById(publication.getGeolocalisation().getId());
        Utilisateur utilisateur = utilisateurRepository.getById(publication.getUtilisateur().getId());
        publication.setGeolocalisation(geolocalisation);
        publication.setUtilisateur(utilisateur);

        return publicationRepository.save(publication);
    }

    @GetMapping(path = "{id}")
    public Publication getPublication(@PathVariable Long id) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        return optionalPublication.orElse(null);
    }

    @GetMapping
    public List<Publication> getAllPublication() {
        List<Publication> publicationList = publicationRepository.findAll();
        return publicationList;
    }

    @PutMapping
    public Publication updatePublication(@PathVariable Long id, @RequestBody Publication publication) {
        Optional<Publication> optionalPublication = publicationRepository.findById(publication.getId());
        Publication existingPublication = optionalPublication.get();
        if (optionalPublication.isPresent()) {
            existingPublication.setTitle(publication.getTitle());
            existingPublication.setCategorie(publication.getCategorie());
            existingPublication.setContenu(publication.getContenu());

            Optional<Geolocalisation> optionalGeolocalisation = geolocalisationRepository.findById(publication.getGeolocalisation().getId());
            if (optionalGeolocalisation.isEmpty()) {
                Geolocalisation geolocalisation = geolocalisationRepository.save(publication.getGeolocalisation());
                existingPublication.setGeolocalisation(geolocalisation);
            } else {
                existingPublication.setGeolocalisation(optionalGeolocalisation.get());
            }
        }
        Publication newPublication = publicationRepository.save(existingPublication);
        return new ResponseEntity<>(newPublication, HttpStatus.OK).getBody();
    }

    @DeleteMapping(path = "{id}")
    public String deletePublication(Long id) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        if (optionalPublication.isPresent()) {
            publicationRepository.deleteById(id);
            return "Publication Supprimée";
        }
        return "Suppression échouée";
    }

    @GetMapping("/utilisateur/{userId}")
    public List<Publication> getPublicationsByUser(@PathVariable Long userId) {
        List<Publication> optionalPublication = publicationRepository.findByUtilisateur_Id(userId);
        return optionalPublication;
    }
}