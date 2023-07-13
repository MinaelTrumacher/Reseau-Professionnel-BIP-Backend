package afpa.mra.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import afpa.mra.entities.Geolocalisation;
import afpa.mra.repositories.GeolocalisationRepository;

@RestController
@RequestMapping(path = "/geolocalisation")
public class GeolocalisationController {

    	@Autowired
	private GeolocalisationRepository geolocalisationRepository ;
	

	@GetMapping("/{codePostal}")
	public List<Geolocalisation> getByCodePostal(@PathVariable String codePostal) {
	    return geolocalisationRepository.findByCodePostal(codePostal);
	}
    
}

