package afpa.mra.controllers;


import afpa.mra.entities.Formation;
import afpa.mra.repositories.FormationRepository;
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
@RequestMapping("/api/formations")
public class FormationController {

    @Autowired
    private FormationRepository formationRepository;

    @PostMapping
    public ResponseEntity<Object> createFormation(@RequestBody Formation formation) {
        Formation newFormation;
        newFormation = formationRepository.save(formation);
        return new ResponseEntity<>(newFormation, HttpStatus.OK);
    }

    @GetMapping
    public List<Formation> getAllFormation() {
        List<Formation> formationList = formationRepository.findAll();
        return formationList;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getFormation(@PathVariable Long id) {
        Optional<Formation> optionalFormation = formationRepository.findById(id);

        if(optionalFormation.isEmpty()) {
            Map<String, Object> body = new HashMap<>();
            body.put("response", "Aucune formation trouvée !");
            return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalFormation.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateFormation(@RequestBody Formation formation) {
        Formation formation1 = formationRepository.save(formation);
        return ResponseEntity.ok("La formation à été mise à jour avec succès.");
    }


    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteFormation(@PathVariable Long id) {
        formationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
