package afpa.mra.controllers;

import afpa.mra.entities.Embauche;
import afpa.mra.repositories.EmbaucheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/embauches")
public class EmbaucheController {

    @Autowired
    private EmbaucheRepository embaucheRepository;

    @PostMapping
    public void addEmbauche(@RequestBody Embauche embauche) {
        embaucheRepository.save(embauche);
    }

    @GetMapping
    public List<Embauche> embaucheList() {
        return embaucheRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Embauche> getEmbauche(@PathVariable("id") Long id) { return embaucheRepository.findById(id); }

    @PutMapping
    public void updateEmbauche(@RequestBody Embauche embauche) {
        embaucheRepository.save(embauche);
    }

    @DeleteMapping("/{id}")
    public void deleteEmbauche(@PathVariable("id") Long id) {
        embaucheRepository.deleteById(id);
    }
}
