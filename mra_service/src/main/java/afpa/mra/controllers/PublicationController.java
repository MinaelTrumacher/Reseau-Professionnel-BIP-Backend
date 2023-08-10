package afpa.mra.controllers;

import afpa.mra.entities.Geolocalisation;
import afpa.mra.entities.Publication;
import afpa.mra.entities.TypePublication;
import afpa.mra.entities.Utilisateur;
import afpa.mra.repositories.GeolocalisationRepository;
import afpa.mra.repositories.InteractionRepository;
import afpa.mra.repositories.PublicationRepository;
import afpa.mra.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    public Publication getPublication(@PathVariable Long id) {
        Publication  publicationList = publicationRepository.getById(id);
        return publicationList;
    }
    
    @GetMapping("/utilisateur/{userId}")
    public List<Publication> getPublicationsByUser(@PathVariable Long userId) {
        List<Publication> optionalPublication = publicationRepository.findByUtilisateur_IdOrderByDateCreationDesc(userId);
        return optionalPublication;
    }

    @GetMapping
    public List<Publication> getAllPublication() {
        List<Publication> publicationList = publicationRepository.findByOrderByDateCreationDesc();
        return publicationList;
    }
    
    @GetMapping(path = "/interactions/{id}")
    public List<Publication> getAllPublicationWithInteractionByUser(@PathVariable Long id) {
        List<Publication> publicationList = publicationRepository.getPublicationWithInteractionByUser(id);
        return publicationList;
    }
    
    @GetMapping(path = "/favoris/{id}")
    public List<Publication> getAllFavoris(Long userId) {
    	System.out.println("test");
        List<Publication> publicationList = publicationRepository.getPublicationWithFavoris(userId);
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

    @PostMapping(path = "/search")
    public List<Publication> getPublicationWithFiltre(@RequestBody Map<String, Object> filterRequest) {
    	List<String> types = (ArrayList<String>) filterRequest.get("types");
    	List<String> keywords = (ArrayList<String>) filterRequest.get("keywords");
    	List<Object> villes = (ArrayList<Object>) filterRequest.get("villes");

        // Permet de gerer manuellement le type des id de villes
        ArrayList<Long> parsedVilles = new ArrayList<>();
        for (Object ville : villes) {
            if (ville instanceof Integer) {
                parsedVilles.add(((Integer) ville).longValue());
            } else if (ville instanceof Long) {
                parsedVilles.add((Long) ville);
            } else if (ville instanceof String) {
                try {
                    parsedVilles.add(Long.parseLong((String) ville));
                } catch (NumberFormatException e) {
                	System.out.println(e);
                }
            }
        }
        
        // Permet de gerer manuellement le type des publications
        List<TypePublication> typePublications = types.stream()
                .map(TypePublication::valueOf)
                .collect(Collectors.toList());
        
        //Si aucun Filtre n'a était selectionné renvoyer simplement les publications avec le bon type
        if (keywords.size() == 0) {
            return villes.size()  == 0
                    ? publicationRepository.findWithFiltre(typePublications)
                    : publicationRepository.findWithFiltre(typePublications, parsedVilles);
        }

        //Recherche de toutes les publications correspondant aux critères
        List<Publication> publicationsCount = keywords.stream()
                .map(keyword -> (villes.size() == 0)
                        ? publicationRepository.findWithFiltre(keyword.toLowerCase(), typePublications)
                        : publicationRepository.findWithFiltre(keyword.toLowerCase(), typePublications, parsedVilles))
                .flatMap(List::stream)
                .collect(Collectors.toList());

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
                int compareByValue = entry2.getValue().compareTo(entry1.getValue());

                if (compareByValue != 0) {
                    return compareByValue; // Tri par doublons
                } else {
                    // Tri par date de création en ordre décroissant si les doublons sont les mêmes
                    return entry2.getKey().getDateCreation().compareTo(entry1.getKey().getDateCreation());
                }
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
