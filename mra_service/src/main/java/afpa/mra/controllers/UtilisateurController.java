package afpa.mra.controllers;

import afpa.mra.entities.Geolocalisation;
import afpa.mra.entities.Utilisateur;
import afpa.mra.repositories.GeolocalisationRepository;
import afpa.mra.repositories.UtilisateurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/utilisateurs")
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;
    private final GeolocalisationRepository geolocalisationRepository;

    public UtilisateurController(UtilisateurRepository utilisateurRepository, GeolocalisationRepository geolocalisationRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.geolocalisationRepository = geolocalisationRepository;
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

    @PostMapping
    public ResponseEntity<Object> CreateUtilisateur(@RequestBody Utilisateur utilisateur) {
        Optional<Geolocalisation> optionalGeolocalisation = geolocalisationRepository.findByLatitudeAndLongitude(utilisateur.getGeolocalisation().getLatitude(), utilisateur.getGeolocalisation().getLongitude());

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

    @PutMapping
    public ResponseEntity<Object> updateUtilisateur(@RequestBody Utilisateur utilisateur) {
        Optional<Geolocalisation> optionalGeolocalisation = geolocalisationRepository.findByLatitudeAndLongitude(utilisateur.getGeolocalisation().getLatitude(), utilisateur.getGeolocalisation().getLongitude());

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
