package afpa.mra.controllers;


import afpa.mra.entities.Formation;
import afpa.mra.entities.Stage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formations")
public class FormationController {
    private final EntityManagerFactory entityManagerFactory;

    public FormationController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @PostMapping
    public ResponseEntity<String> createFormation(@RequestBody Formation formation) {
        if (formation.getTitre() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le titre de la formation n'a pas été renseigné.");
        }

        try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(formation);
                transaction.commit();
                return ResponseEntity.status(HttpStatus.CREATED).body("Le stage a été crée avec succès.");
            } catch (Exception e) {
                if(transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la création de la formation.");
            }
        }
    }

    @GetMapping
    public ResponseEntity<List<Formation>> getAllFormations() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            List<Formation> formations = entityManager.createQuery("SELECT f from Formation f", Formation.class)
                    .getResultList();
            return ResponseEntity.ok(formations);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Formation formation = entityManager.find(Formation.class, id);
            if(formation != null) {
                return ResponseEntity.ok(formation);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFormation(@PathVariable Long id, @RequestBody Formation updatedFormation) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                Formation formation = entityManager.find(Formation.class, id);
                if (formation != null) {
                    formation.setTitre(updatedFormation.getTitre());
                    entityManager.merge(formation);
                    transaction.commit();
                    return ResponseEntity.ok("La formation à été mise à jour avec succès.");
                }
                else {
                    return ResponseEntity.notFound().build();
                }
            }
            catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la mise à jour de la formation.");
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFormation(@PathVariable Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                Formation formation = entityManager.find(Formation.class, id);
                if (formation != null) {
                    // Vérifier s'il existe des stages associés à la formation
                    List<Stage> stages = entityManager.createQuery("SELECT s FROM Stage s WHERE s.formation.id = :formationId", Stage.class)
                            .setParameter("formationId", id)
                            .getResultList();

                    if (!stages.isEmpty()) {
                        // Dissocier les stages de la formation
                        for (Stage stage : stages) {
                            stage.setFormation(null);
                            entityManager.merge(stage);
                        }
                    }

                    entityManager.remove(formation);
                    transaction.commit();
                    return ResponseEntity.ok("Formation supprimée avec succès.");
                } else {
                    return ResponseEntity.notFound().build();
                }
            } catch (EntityNotFoundException e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Une erreur s'est produite lors de la suppression de la formation.");
            }
        }
    }
}
