package afpa.mra.controllers;

import afpa.mra.entities.Session;
import afpa.mra.entities.Suivi;
import afpa.mra.entities.Utilisateur;
import afpa.mra.repositories.SessionRepository;
import afpa.mra.repositories.SuiviRepository;
import afpa.mra.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/suivis")
public class SuiviController {

    @Autowired
    private SuiviRepository suiviRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping
    public Suivi createSuivi(@RequestBody Suivi suivi) {
        Utilisateur utilisateur = utilisateurRepository.getById(suivi.getUtilisateur().getId());
        Session session = sessionRepository.getById(suivi.getSession().getId());
        suivi.setUtilisateur(utilisateur);
        suivi.setSession(session);

        return suiviRepository.save(suivi);
    }

    @GetMapping
    public List<Suivi> getAllSuivi() {
        List<Suivi> suiviList = suiviRepository.findAll();
        return suiviList;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getSuivi(@PathVariable Long id) {
        Optional<Suivi> optionalSuivi = suiviRepository.findById(id);
        if (optionalSuivi.isEmpty()) {
            Map<String, Object> body = new HashMap<>();
            body.put("response", "Aucun suivi trouvé !");
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalSuivi.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateSuivi(@RequestBody Suivi suivi) {
        Optional<Session> optionalSession = sessionRepository.findById(suivi.getSession().getId());
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(suivi.getUtilisateur().getId());

        Suivi suivi1;

        if (optionalSession.isEmpty()) {
            Session session = sessionRepository.save(suivi.getSession());
            suivi.setSession(session);
        } else  {
            suivi.setSession(optionalSession.get());
        }

        if (optionalUtilisateur.isEmpty()) {
            Utilisateur utilisateur = utilisateurRepository.save(suivi.getUtilisateur());
            suivi.setUtilisateur(utilisateur);
        } else {
            suivi.setUtilisateur(optionalUtilisateur.get());
        }

        suivi1 = suiviRepository.save(suivi);
        return new ResponseEntity<>(suivi1, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteSuivi(@PathVariable Long id) {
        suiviRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
