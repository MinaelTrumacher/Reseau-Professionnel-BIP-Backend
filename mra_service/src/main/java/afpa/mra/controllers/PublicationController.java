package afpa.mra.controllers;

import afpa.mra.entities.Publication;
import afpa.mra.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/publications")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;

    @Autowired
    public void PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping
    public Publication createPublication(@RequestBody Publication publication) {
        return publicationService.createPublication(publication);
    }

    @GetMapping("/{id}")
    public Publication getPublication(@PathVariable Long id) {

        return publicationService.getPublication(id);
    }

    @GetMapping
    public List<Publication> getAllPublication() {
        return publicationService.getAllPublication();
    }

    @PutMapping("/{id}")
    public Publication updatePublication(@PathVariable Long id, @RequestBody Publication publication) {
        return publicationService.updatePublication(publication);
    }

    @DeleteMapping("/{id}")
    public void deletePublication(@PathVariable Long id) {
        publicationService.deletePublication(id);
    }


    @GetMapping("/utilisateur/{userId}")
    public List<Publication> getPublicationsByUser(@PathVariable Long userId) {
        return publicationService.getPublicationsByUser(userId);
    }
    //getFavorisByUser
//    getPostulerByUser
}