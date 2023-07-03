package afpa.mra.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import afpa.mra.entities.Publication;
import afpa.mra.repositories.PublicationRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/publication")
public class PublicationController {
	
	@Autowired
	private PublicationRepository publicationRepository ;
	
    @GetMapping
    public List<Publication> getAllPublication() {
        return publicationRepository.findAll();
    }
    
	@PostMapping
	public Publication AddPublication(@RequestBody Publication publication) {
		return publicationRepository.save(publication);
	}
	
	@PutMapping
	public Publication UpdatePublication(@RequestBody Publication publication) {
		return publicationRepository.save(publication);
	}
	
    @DeleteMapping("/{id}")
    public void deletePublication(@PathVariable Long id) {
        publicationRepository.deleteById(id);
    }

}
