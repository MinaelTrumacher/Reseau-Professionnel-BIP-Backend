package afpa.mra.controllers;

import afpa.mra.entities.Entreprise;
import afpa.mra.repositories.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/entreprises")
public class EntrepriseController {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @PostMapping
    public void createEntreprise(@RequestBody Entreprise entreprise) {
        entrepriseRepository.save(entreprise);
    }

    @GetMapping
    public List<Entreprise> getAllEntrepreprise() {
        return entrepriseRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Optional<Entreprise> getEntreprise(@PathVariable("id") Long id) { return entrepriseRepository.findById(id); }

    @PutMapping
    public void updateEntreprise(@RequestBody Entreprise entreprise) { entrepriseRepository.save(entreprise); }

    @DeleteMapping(path = "{id}")
    public void deleteEntreprise(@PathVariable("id") Long id) { entrepriseRepository.deleteById(id); }
}
