package afpa.mra.controller;
import afpa.mra.configuration_token.ConfigurationToken;
import afpa.mra.entity.Utilisateur;
import afpa.mra.repository.UtilisateurRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
@CrossOrigin({"http://localhost:4200", "http://localhost:4201"})
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public ConfigurationToken configurationToken;

    @Autowired
    private HttpServletRequest request;

    // Récupérer tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs(@RequestHeader(value = "authorization", required = false) String authorization) {
        String adresseAppelante = request.getHeader("Referer");
        return configurationToken.verifier(authorization, adresseAppelante, utilisateurRepository.findAll());
    }

    @GetMapping("/{email}/email")
    public ResponseEntity<Utilisateur> getUtilisateurByEmail(@PathVariable("email") String email, @RequestHeader(value = "authorization", required = false) String authorization) {
        String adresseAppelante = request.getHeader("Referer");
        return configurationToken.verifier(authorization, adresseAppelante, utilisateurRepository.getByEmail(email));
    }

    // Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable("id") Integer id, @RequestHeader(value = "authorization", required = false) String authorization) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        if (utilisateur != null) {
            String adresseAppelante = request.getHeader("Referer");
            return configurationToken.verifier(authorization, adresseAppelante, utilisateur);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Créer un nouvel utilisateur
    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur, @RequestHeader(value = "authorization", required = false) String authorization) {
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        String adresseAppelante = request.getHeader("Referer");
        return configurationToken.verifier(authorization, adresseAppelante, savedUtilisateur);
    }

    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable("id") Integer id, @RequestBody Utilisateur utilisateur, @RequestHeader(value = "authorization", required = false) String authorization) {
        if (utilisateurRepository.existsById(id)) {
            utilisateur.setIdUtilisateur(id);
            Utilisateur updatedUtilisateur = utilisateurRepository.save(utilisateur);
            String adresseAppelante = request.getHeader("Referer");
            return configurationToken.verifier(authorization,adresseAppelante, updatedUtilisateur);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUtilisateur(@PathVariable("id") Integer id, @RequestHeader(value = "authorization", required = false) String authorization) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            String adresseAppelante = request.getHeader("Referer");
            return configurationToken.verifier(authorization, adresseAppelante);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
