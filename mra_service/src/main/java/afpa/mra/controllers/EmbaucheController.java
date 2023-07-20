package afpa.mra.controllers;

import afpa.mra.entities.*;
import afpa.mra.repositories.EmbaucheRepository;
import afpa.mra.repositories.EntrepriseRepository;
import afpa.mra.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/embauches")
public class EmbaucheController {

    @Autowired
    private EmbaucheRepository embaucheRepository;

    @Autowired
    private  EntrepriseRepository entrepriseRepository;

    @Autowired
    private  UtilisateurRepository utilisateurRepository;

    @PostMapping
    public ResponseEntity<Object> createEmbauche(@RequestBody Embauche embauche) {
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(embauche.getEntreprise().getId());
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(embauche.getUtilisateur().getId());

        Embauche embauche1;

        if (optionalEntreprise.isEmpty()) {
            Entreprise entreprise = entrepriseRepository.save(embauche.getEntreprise());
            embauche.setEntreprise(entreprise);
        } else {
            embauche.setEntreprise(optionalEntreprise.get());
        }

        if (optionalUtilisateur.isEmpty()) {
            Utilisateur utilisateur = utilisateurRepository.save(embauche.getUtilisateur());
            embauche.setUtilisateur(utilisateur);
        } else {
            embauche.setUtilisateur(optionalUtilisateur.get());
        }

        embauche1 = embaucheRepository.save(embauche);
        return new ResponseEntity<>(embauche1, HttpStatus.OK);
    }

    @GetMapping
    public List<Map<String, Object>> getAllEmbauche() {
        List<Embauche> embaucheList = embaucheRepository.findAll();

        List<Map<String, Object>> responseList = new ArrayList<>();
        for (Embauche embauche : embaucheList) {
            Map<String, Object> embaucheMap = new HashMap<>();
            embaucheMap.put("id", embauche.getId());
            embaucheMap.put("dateDebut", embauche.getDateDebut());
            embaucheMap.put("dateFin", embauche.getDateFin());

            // Retrieve the associated Utilisateur object
            Utilisateur utilisateur = embauche.getUtilisateur();
            Map<String, Object> utilisateurMap = new HashMap<>();
            utilisateurMap.put("id", utilisateur.getId());
            utilisateurMap.put("nom", utilisateur.getNom());
            utilisateurMap.put("prenom", utilisateur.getPrenom());
            // Add other relevant utilisateur fields here

            // Retrieve the associated Entreprise object
            Entreprise entreprise = embauche.getEntreprise();
            Map<String, Object> entrepriseMap = new HashMap<>();
            entrepriseMap.put("id", entreprise.getId());
            entrepriseMap.put("raisonSociale", entreprise.getRaisonSociale());
            entrepriseMap.put("siret", entreprise.getSiret());
            // Add other relevant entreprise fields here

            embaucheMap.put("utilisateur", utilisateurMap);
            embaucheMap.put("entreprise", entrepriseMap);

            responseList.add(embaucheMap);
        }
        return responseList;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getEmbauche(@PathVariable Long id) {
        Embauche embauche = embaucheRepository.findById(id).orElse(null);
        if (embauche == null) {
            // Si le stage n'est pas trouvé, retourner une réponse avec un message d'erreur
            Map<String, Object> body = new HashMap<>();
            body.put("response", "Aucun stage trouvé !");
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        // Créer une nouvelle map de réponse qui inclut l'objet Utilisateur
        Map<String, Object> embaucheMap = new HashMap<>(); //responseMap remplacé par embaucheMap
        /*responseMap.put("embauche", optionalEmbauche.get());
        responseMap.put("utilisateur", utilisateur);*/

        embaucheMap.put("id", embauche.getId());
        embaucheMap.put("dateDebut", embauche.getDateDebut());
        embaucheMap.put("dateFin", embauche.getDateFin());
        embaucheMap.put("utilisateur", embauche.getUtilisateur());
        embaucheMap.put("entreprise", embauche.getEntreprise());


        // Retourner la réponse JSON
        return new ResponseEntity<>(embaucheMap, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateEmbauche(@RequestBody Embauche embauche) {
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(embauche.getEntreprise().getId());
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(embauche.getUtilisateur().getId());

        Embauche embauche1;

        if (optionalEntreprise.isEmpty()) {
            Entreprise entreprise = entrepriseRepository.save(embauche.getEntreprise());
            embauche.setEntreprise(entreprise);
        } else {
            embauche.setEntreprise(optionalEntreprise.get());
        }

        if (optionalUtilisateur.isEmpty()) {
            Utilisateur utilisateur = utilisateurRepository.save(embauche.getUtilisateur());
            embauche.setUtilisateur(utilisateur);
        } else {
            embauche.setUtilisateur(optionalUtilisateur.get());
        }

        embauche1 = embaucheRepository.save(embauche);
        return new ResponseEntity<>(embauche1, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteEmbauche(@PathVariable Long id) {
        embaucheRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

