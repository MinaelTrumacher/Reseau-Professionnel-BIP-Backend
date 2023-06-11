package afpa.mra.controller;
import afpa.mra.configuration_token.ConfigurationToken;
import afpa.mra.entity.Utilisateur;
import afpa.mra.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public ConfigurationToken configurationToken;

    // Récupérer tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs(@RequestHeader(value = "authorization", required = false) String authorization) {
        return new ResponseEntity<>(utilisateurRepository.findAll(), HttpStatus.OK);
    }

    // Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable("id") Integer id, @RequestHeader(value = "authorization", required = false) String authorization) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        if (utilisateur != null) {
            return configurationToken.verifier(authorization, utilisateur);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Créer un nouvel utilisateur
    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur, @RequestHeader(value = "authorization", required = false) String authorization) {
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        return configurationToken.verifier(authorization, savedUtilisateur);
    }

    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable("id") Integer id, @RequestBody Utilisateur utilisateur, @RequestHeader(value = "authorization", required = false) String authorization) {
        if (utilisateurRepository.existsById(id)) {
            utilisateur.setIdUtilisateur(id);
            Utilisateur updatedUtilisateur = utilisateurRepository.save(utilisateur);
            return configurationToken.verifier(authorization, updatedUtilisateur);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUtilisateur(@PathVariable("id") Integer id, @RequestHeader(value = "authorization", required = false) String authorization) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return configurationToken.verifier(authorization);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
