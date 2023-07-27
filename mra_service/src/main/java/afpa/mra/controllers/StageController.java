package afpa.mra.controllers;

import afpa.mra.entities.*;
import afpa.mra.repositories.EntrepriseRepository;
import afpa.mra.repositories.FormationRepository;
import afpa.mra.repositories.StageRepository;
import afpa.mra.repositories.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/stages")
public class StageController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private StageRepository stageRepository;

    @PostMapping
    public ResponseEntity<Object> createStage(@RequestBody Stage stage) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(stage.getUtilisateur().getId());
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(stage.getEntreprise().getId());
        Optional<Formation> optionalFormation = formationRepository.findById(stage.getFormation().getId());

        Stage stage1;

        if (optionalUtilisateur.isEmpty()) {
            Utilisateur utilisateur = utilisateurRepository.save(stage.getUtilisateur());
            stage.setUtilisateur(utilisateur);
        } else {
            stage.setUtilisateur(optionalUtilisateur.get());
        }

        if (optionalEntreprise.isEmpty()) {
            Entreprise entreprise = entrepriseRepository.save((stage.getEntreprise()));
            stage.setEntreprise(entreprise);
        } else  {
            stage.setEntreprise(optionalEntreprise.get());
        }

        if (optionalFormation.isEmpty()) {
            Formation formation = formationRepository.save((stage.getFormation()));
            stage.setFormation(formation);
        } else {
            stage.setFormation(optionalFormation.get());
        }

        stage1 = stageRepository.save(stage);
        return new ResponseEntity<>(stage1, HttpStatus.OK);
    }

    @GetMapping
    public List<Map<String, Object>> getAllStage() {
        List<Stage> stageList = stageRepository.findAll();

        List<Map<String, Object>> responseList = new ArrayList<>();
        for (Stage stage : stageList) {
            Map<String, Object> stageMap = new HashMap<>();
            stageMap.put("id", stage.getId());
            stageMap.put("type", stage.getType());
            stageMap.put("dateDebut", stage.getDateDebut());
            stageMap.put("dateFin", stage.getDateFin());

            // Retrieve the associated Utilisateur object
            Utilisateur utilisateur = stage.getUtilisateur();
            Map<String, Object> utilisateurMap = new HashMap<>();
            utilisateurMap.put("id", utilisateur.getId());
            utilisateurMap.put("nom", utilisateur.getNom());
            utilisateurMap.put("prenom", utilisateur.getPrenom());
            // Add other relevant utilisateur fields here

            // Retrieve the associated Entreprise object
            Entreprise entreprise = stage.getEntreprise();
            Map<String, Object> entrepriseMap = new HashMap<>();
            entrepriseMap.put("id", entreprise.getId());
            entrepriseMap.put("raisonSociale", entreprise.getRaisonSociale());
            entrepriseMap.put("siret", entreprise.getSiret());
            // Add other relevant entreprise fields here

            // Retrieve the associated Formation object
            Formation formation = stage.getFormation();
            Map<String, Object> formationMap = new HashMap<>();
            formationMap.put("id", formation.getId());
            formationMap.put("titre", formation.getTitre());
            formationMap.put("codeRncp", formation.getCodeRncp());
            // Add other relevant entreprise fields here

            stageMap.put("utilisateur", utilisateurMap);
            stageMap.put("entreprise", entrepriseMap);
            stageMap.put("formation", entrepriseMap);

            responseList.add(stageMap);
        }
        return responseList;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getStage(@PathVariable Long id) {
        Stage stage = stageRepository.findById(id).orElse(null);
        if (stage == null) {
            // Si le stage n'est pas trouvé, retourner une réponse avec un message d'erreur
            Map<String, Object> body = new HashMap<>();
            body.put("response", "Aucun stage trouvé !");
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        // Récupérer l'objet Utilisateur associé en utilisant l'id_utilisateur
        Utilisateur utilisateur = stage.getUtilisateur();
        utilisateur.setStages(null); // Set the utilisateur to null to avoid infinite recursion in JSON serialization


        // Créer une nouvelle map de réponse qui inclut l'objet Utilisateur
        Map<String, Object> stageMap = new HashMap<>(); //responseMap remplacé par stageMap
        stageMap.put("id", stage.getId());
        stageMap.put("type", stage.getType());
        stageMap.put("dateDebut", stage.getDateDebut());
        stageMap.put("dateFin", stage.getDateFin());
        stageMap.put("utilisateur", utilisateur);
        stageMap.put("entreprise", stage.getEntreprise());
        stageMap.put("formation", stage.getFormation());

        // Retourner la réponse JSON
        return new ResponseEntity<>(stageMap, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateStage(@RequestBody Stage stage) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(stage.getUtilisateur().getId());
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(stage.getEntreprise().getId());
        Optional<Formation> optionalFormation = formationRepository.findById(stage.getFormation().getId());

        Stage stage1;

        if (optionalUtilisateur.isEmpty()) {
            Utilisateur utilisateur = utilisateurRepository.save(stage.getUtilisateur());
            stage.setUtilisateur(utilisateur);
        } else {
            stage.setUtilisateur(optionalUtilisateur.get());
        }

        if (optionalEntreprise.isEmpty()) {
            Entreprise entreprise = entrepriseRepository.save((stage.getEntreprise()));
            stage.setEntreprise(entreprise);
        } else  {
            stage.setEntreprise(optionalEntreprise.get());
        }

        if (optionalFormation.isEmpty()) {
            Formation formation = formationRepository.save((stage.getFormation()));
            stage.setFormation(formation);
        } else {
            stage.setFormation(optionalFormation.get());
        }

        stage1 = stageRepository.save(stage);
        return new ResponseEntity<>(stage1, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteStage(@PathVariable Long id) {
        stageRepository.deleteById(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
