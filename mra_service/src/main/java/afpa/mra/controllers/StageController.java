package afpa.mra.controllers;

import afpa.mra.entities.*;
import afpa.mra.repositories.EntrepriseRepository;
import afpa.mra.repositories.FormationRepository;
import afpa.mra.repositories.StageRepository;
import afpa.mra.repositories.UtilisateurRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/stages")
public class StageController {
    @Autowired
    private final EntityManagerFactory entityManagerFactory;
    @Autowired
    private final UtilisateurRepository utilisateurRepository;
    @Autowired
    private final EntrepriseRepository entrepriseRepository;
    @Autowired
    private final FormationRepository formationRepository;
    @Autowired
    private final StageRepository stageRepository;

    @Autowired
    public StageController(EntityManagerFactory entityManagerFactory,
                           UtilisateurRepository utilisateurRepository,
                           EntrepriseRepository entrepriseRepository,
                           FormationRepository formationRepository, StageRepository stageRepository) {
        this.entityManagerFactory = entityManagerFactory;
        this.utilisateurRepository = utilisateurRepository;
        this.entrepriseRepository = entrepriseRepository;
        this.formationRepository = formationRepository;
        this.stageRepository = stageRepository;
    }

    @PostMapping
    public ResponseEntity<String> createStage(@RequestBody Stage stage) {
        if (stage.getType() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le type du stage n'a pas été précisé.");
        }

        Utilisateur existingUtilisateur = utilisateurRepository.findById(stage.getUtilisateur().getId()).orElse(null);
        if (existingUtilisateur != null) {
            stage.setUtilisateur(existingUtilisateur);
        }

        Entreprise existingEntreprise = entrepriseRepository.findById(stage.getEntreprise().getId()).orElse(null);
        if (existingEntreprise != null) {
            stage.setEntreprise(existingEntreprise);
        }

        Formation existingFormation = formationRepository.findById(stage.getFormation().getId()).orElse(null);
        if (existingFormation != null) {
            stage.setFormation(existingFormation);
        }

        Stage createdStage = stageRepository.save(stage);
        if (createdStage != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Le stage a été créé avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la création du stage");
        }
    }

   /* @GetMapping
    public ResponseEntity<List<Stage>> getAllStages() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            List<Stage> stages = entityManager.createQuery("SELECT s FROM Stage s", Stage.class)
                    .getResultList();
            return ResponseEntity.ok(stages);
        }
    }*/
    @GetMapping
    public List<Stage> getAllStage() {
        List<Stage> stageList = stageRepository.findAll();
        return stageList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Stage stage = entityManager.find(Stage.class, id);
            if (stage != null) {
                return ResponseEntity.ok(stage);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStage(@PathVariable Long id, @RequestBody Stage updatedStage) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                Stage stage = entityManager.find(Stage.class, id);
                if (stage != null) {
                    stage.setType(updatedStage.getType());
                    // Mettez à jour les autres attributs du stage selon vos besoins
                    entityManager.merge(stage);
                    transaction.commit();
                    return ResponseEntity.ok("Stage mis à jour avec succès");
                } else {
                    return ResponseEntity.notFound().build();
                }
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Une erreur s'est produite lors de la mise à jour du stage");
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStage(@PathVariable Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                Stage stage = entityManager.find(Stage.class, id);
                if (stage != null) {
                    // Dissocier l'utilisateur et la formation du stage
                    stage.setUtilisateur(null);
                    stage.setFormation(null);

                    entityManager.remove(stage);
                    transaction.commit();
                    return ResponseEntity.ok("Stage supprimé avec succès");
                } else {
                    return ResponseEntity.notFound().build();
                }
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Une erreur s'est produite lors de la suppression du stage");
            }
        }
    }
}