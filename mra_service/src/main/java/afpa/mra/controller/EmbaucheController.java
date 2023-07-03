package afpa.mra.controller;

import afpa.mra.entities.Embauche;
import afpa.mra.repository.EmbaucheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/embauche")
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
    public Embauche getEmbauche(@PathVariable("id") Long id) {
        return embaucheRepository.getById(id);
    }

    @PutMapping
    public void updateEmbauche(@RequestBody Embauche embauche) {
        embaucheRepository.save(embauche);
    }

    @DeleteMapping("/{id}")
    public void deleteEmbauche(@PathVariable("id") Long id) {
        embaucheRepository.deleteById(id);
    }
}

