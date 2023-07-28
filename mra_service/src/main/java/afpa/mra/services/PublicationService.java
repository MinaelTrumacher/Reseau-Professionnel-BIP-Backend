package afpa.mra.services;

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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final GeolocalisationRepository geolocalisationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final InteractionRepository interactionRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository, GeolocalisationRepository geolocalisationRepository, UtilisateurRepository utilisateurRepository, InteractionRepository interactionRepository) {
        this.publicationRepository = publicationRepository;
        this.geolocalisationRepository = geolocalisationRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.interactionRepository = interactionRepository;
    }

    public Publication createPublication(Publication publication) {
        Geolocalisation geolocalisation = geolocalisationRepository.getById(publication.getGeolocalisation().getId());
        Utilisateur utilisateur = utilisateurRepository.getById(publication.getUtilisateur().getId());
        publication.setGeolocalisation(geolocalisation);
        publication.setUtilisateur(utilisateur);

        return publicationRepository.save(publication);
    }

    public Publication getPublication(Long id) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        return optionalPublication.orElse(null);
    }

    public List<Publication> getAllPublication() {
        List<Publication> publicationList = publicationRepository.findAll();
        return publicationList;
    }

    public Publication updatePublication(Publication publication) {
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

    public void deletePublication(Long id) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        if (optionalPublication.isPresent()) {
            publicationRepository.deleteById(id);
        }
    }

    //retourner la liste des publications d'un utilisateur
    public List<Publication> getPublicationsByUser(Long userId) {
        List<Publication> optionalPublication = publicationRepository.findByUtilisateur_Id(userId);
        return optionalPublication;
    }
}