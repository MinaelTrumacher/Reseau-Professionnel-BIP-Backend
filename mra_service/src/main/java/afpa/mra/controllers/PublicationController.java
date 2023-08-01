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

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/publications")
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
    public List <Publication> getPublication(@PathVariable Long id) {
        List<Publication>  publicationList = publicationRepository.findByUtilisateur_Id(id);
        return publicationList;
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

    @PostMapping(path = "/search")
    public List<Publication> getPublicationWithFiltre(@RequestBody Map<String, String[]> filterRequest) {
        String[] types = filterRequest.get("types");
        String[] keywords = filterRequest.get("keywords");
        String[] villes = filterRequest.get("ville");
        
        //Si aucun Filtre n'a était selectionné renvoyer simplement les publications avec le bon type
        if(keywords.length == 0) {
            return publicationRepository.findWithFiltre(types,villes[0]);
        }

        //Recherche de toutes les publications correspondant aux critères
        List<Publication> publicationsCount = new ArrayList<>();
        for (String keyword : keywords) {
            publicationsCount.addAll(publicationRepository.findWithFiltre(keyword.toLowerCase(), types,villes[0]));
        }

        //Calcul du nombre de doublons pour un meilleur référencement
        HashMap<Publication, Integer> publicationCount = new HashMap<>();
        for (Publication publication : publicationsCount) {
            if (publicationCount.containsKey(publication)) {
                int count = publicationCount.get(publication);
                publicationCount.put(publication, count + 1);
            } else {
                publicationCount.put(publication, 1);
            }
        }

        //Tri par doublons
        List<Map.Entry<Publication, Integer>> listMapped = new ArrayList<>(publicationCount.entrySet());
        Collections.sort(listMapped, new Comparator<Map.Entry<Publication, Integer>>() {
            @Override
            public int compare(Map.Entry<Publication, Integer> entry1, Map.Entry<Publication, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        //Map to List
        List<Publication> publications = new ArrayList<>();
        for (Map.Entry<Publication, Integer> entry : listMapped) {
            Publication publication = entry.getKey();
            publications.add(publication);
        }
        return publications;
    }
}