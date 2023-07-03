package afpa.mra.controller;

import afpa.mra.entities.Utilisateur;
import afpa.mra.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping
    public void addUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    @GetMapping
    public List<Utilisateur> utilisateursList() {
        return utilisateurRepository.findAll();
    }

    @GetMapping("/{id}")
    public Utilisateur getUtilisateur(@PathVariable("id") Long id) {
        return utilisateurRepository.getById(id);
    }

    @PutMapping
    public void updateUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    @DeleteMapping("/{id}")
    public void deleteEmbauche(@PathVariable("id") Long id) {
        utilisateurRepository.deleteById(id);
    }

}
