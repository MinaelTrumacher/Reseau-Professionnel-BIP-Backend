package afpa.mra.controllers;

import afpa.mra.entities.Geolocalisation;
import afpa.mra.entities.Utilisateur;
import afpa.mra.repositories.EmbaucheRepository;
import afpa.mra.repositories.GeolocalisationRepository;
import afpa.mra.repositories.StageRepository;
import afpa.mra.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private GeolocalisationRepository geolocalisationRepository;

    @Autowired
    private EmbaucheRepository embaucheRepository;

    @Autowired
    private StageRepository stageRepository;

    @PostMapping
    public ResponseEntity<Object> createUtilisateur(@RequestBody Utilisateur utilisateur) {
//        Optional<Geolocalisation> optionalGeolocalisation = geolocalisationRepository.findByLatitudeAndLongitude(utilisateur.getGeolocalisation().getLatitude(), utilisateur.getGeolocalisation().getLongitude());
        Optional<Geolocalisation> optionalGeolocalisation = geolocalisationRepository.findById(utilisateur.getGeolocalisation().getId());

        Utilisateur utilisateur1;

        if (optionalGeolocalisation.isEmpty()) {
            Geolocalisation geolocalisation = geolocalisationRepository.save(utilisateur.getGeolocalisation());
            utilisateur.setGeolocalisation(geolocalisation);
        } else {
            utilisateur.setGeolocalisation(optionalGeolocalisation.get());
        }

        utilisateur1 = utilisateurRepository.save(utilisateur);
        return new ResponseEntity<>(utilisateur1, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getUtilisateur(@PathVariable Long id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

        if (optionalUtilisateur.isEmpty()) {
            Map<String, Object> body = new HashMap<>();
            body.put("response", "Aucun utilisateur trouvé !");
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateUtilisateur(@RequestBody Utilisateur utilisateur) {
       // Optional<Geolocalisation> optionalGeolocalisation = geolocalisationRepository.findByLatitudeAndLongitude(utilisateur.getGeolocalisation().getLatitude(), utilisateur.getGeolocalisation().getLongitude());
        Optional<Geolocalisation> optionalGeolocalisation = geolocalisationRepository.findById(utilisateur.getGeolocalisation().getId());

        Utilisateur utilisateur1;

        if (optionalGeolocalisation.isEmpty()) {
            Geolocalisation geolocalisation = geolocalisationRepository.save(utilisateur.getGeolocalisation());
            utilisateur.setGeolocalisation(geolocalisation);
        } else {
            utilisateur.setGeolocalisation(optionalGeolocalisation.get());
        }
        utilisateur1 = utilisateurRepository.save(utilisateur);
        return new ResponseEntity<>(utilisateur1, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteUtilisateur(@PathVariable Long id) {
        utilisateurRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
