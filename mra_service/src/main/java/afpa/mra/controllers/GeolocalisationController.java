package afpa.mra.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import afpa.mra.entities.Geolocalisation;
import afpa.mra.repositories.GeolocalisationRepository;
import afpa.mra.services.GeolocalisationService;

@RestController
@RequestMapping("/geolocalisations")
public class GeolocalisationController {

    @Autowired
	private GeolocalisationRepository geolocalisationRepository ;
	@Autowired
	private GeolocalisationService	geolocalisationService;
	

	@GetMapping("/codes-postaux/{codePostal}")
	public List<Geolocalisation> getByCodePostal(@PathVariable String codePostal) {
	    return geolocalisationRepository.findByCodePostal(codePostal);
	}

	@GetMapping("/{id}")
    public Geolocalisation getGeolocalisation(@PathVariable Long id) {
        return geolocalisationService.getGeolocalisation(id);
    }

    @GetMapping
    public List<Geolocalisation> getAllGeolocalisation() {
        return geolocalisationService.getAllGeolocalisation();
    }
    @PutMapping("/{id}")
    public Geolocalisation updateGeolocalisation(@PathVariable Long id, @RequestBody Geolocalisation geolocalisation) {
        return geolocalisationService.updateGeolocalisation(id, geolocalisation);
    }

    @DeleteMapping("/{id}")
    public void deleteGeolocalisation(@PathVariable Long id) {
        geolocalisationService.deleteGeolocalisation(id);
    }
    
}

