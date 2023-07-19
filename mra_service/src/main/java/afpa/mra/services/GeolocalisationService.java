package afpa.mra.services;

import afpa.mra.entities.Geolocalisation;
import afpa.mra.repositories.GeolocalisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeolocalisationService {

    private final GeolocalisationRepository geolocalisationRepository;

    @Autowired
    public GeolocalisationService(GeolocalisationRepository geolocalisationRepository) {
        this.geolocalisationRepository = geolocalisationRepository;
    }

    public Geolocalisation createGeolocalisation(Geolocalisation geolocalisation) {
        return geolocalisationRepository.save(geolocalisation);
    }

    public Geolocalisation getGeolocalisation(Long id) {
        return geolocalisationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Géolocalisation non trouvée avec l'id: " + id));
    }

    public List<Geolocalisation> getAllGeolocalisation() {
        List<Geolocalisation> geolocalisationList = geolocalisationRepository.findAll();
        return geolocalisationList;
    }

    public Geolocalisation updateGeolocalisation(Long id, Geolocalisation geolocalisation) {
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

    public void deleteGeolocalisation(Long id) {
        geolocalisationRepository.deleteById(id);
    }
}
