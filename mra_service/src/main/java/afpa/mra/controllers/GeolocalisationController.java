package afpa.mra.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import afpa.mra.entities.Geolocalisation;
import afpa.mra.repositories.GeolocalisationRepository;
import afpa.mra.services.GeolocalisationService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/geolocalisations")
public class GeolocalisationController {

    @Autowired
    private GeolocalisationRepository geolocalisationRepository;

    @PostMapping
    public Geolocalisation createGeolocalisation(@RequestBody Geolocalisation geolocalisation) {
        return geolocalisationRepository.save(geolocalisation);
    }

    @GetMapping("/codes-postaux/{codePostal}")
    public List<Geolocalisation> getByCodePostal(@PathVariable String codePostal) {
        return geolocalisationRepository.findByCodePostal(codePostal);
    }

    @GetMapping
    public List<Geolocalisation> getAllGeolocalisation() {
        List<Geolocalisation> geolocalisationList = geolocalisationRepository.findAll();
        return geolocalisationList;
    }

    @GetMapping(path = "{id}")
    public Geolocalisation getGeolocalisation(@PathVariable Long id) {
        return geolocalisationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Géolocalisation non trouvée avec l'id: " + id));
    }

    @PutMapping
    public Geolocalisation updateGeolocalisation(@PathVariable Long id, @RequestBody Geolocalisation geolocalisation) {
        Optional<Geolocalisation> optionalGeolocalisation = geolocalisationRepository.findById(id);

        Geolocalisation existingGeolocalisation = optionalGeolocalisation.get();
        if (optionalGeolocalisation.isPresent()) {
            existingGeolocalisation.setVille(geolocalisation.getVille());
            existingGeolocalisation.setRegion(geolocalisation.getRegion());
            existingGeolocalisation.setLatitude(geolocalisation.getLatitude());
            existingGeolocalisation.setLongitude(geolocalisation.getLongitude());
        }
        return geolocalisationRepository.save(existingGeolocalisation);
    }

    @DeleteMapping(path = "{id}")
    public void deleteGeolocalisation(@PathVariable Long id) {
        geolocalisationRepository.deleteById(id);
    }
}

