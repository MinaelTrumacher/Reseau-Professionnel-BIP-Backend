package afpa.mra.controllers;

import afpa.mra.entities.Entreprise;
import afpa.mra.repositories.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/entreprises")
public class EntrepriseController {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @PostMapping
    public void addEntreprise(@RequestBody Entreprise entreprise) {
        entrepriseRepository.save(entreprise);
    }

    @GetMapping
    public List<Entreprise> entreprepriseList() {
        return entrepriseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Entreprise> getEntreprise(@PathVariable("id") Long id) {
        return entrepriseRepository.findById(id);
    }

    @PutMapping
    public void updateEntreprise(@RequestBody Entreprise entreprise) {
        entrepriseRepository.save(entreprise);
    }

    @DeleteMapping("/{id}")
    public void deleteEntreprise(@PathVariable("id") Long id) {
        entrepriseRepository.deleteById(id);
    }
}
