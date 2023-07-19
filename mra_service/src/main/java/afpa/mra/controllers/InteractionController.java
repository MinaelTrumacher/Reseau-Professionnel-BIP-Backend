package afpa.mra.controllers;

import afpa.mra.entities.Interaction;
import afpa.mra.services.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/interactions")
public class InteractionController {
    
    private InteractionService interactionService;

    @Autowired
    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @PostMapping
    public Interaction createInteraction(@RequestBody Interaction interaction) {
        return interactionService.createInteraction(interaction);
//        return interaction;
    }

    @GetMapping("/{id}")
    public Interaction getInteraction(@PathVariable Long id) {

        return interactionService.getInteraction(id);
    }

    @GetMapping
    public List<Interaction> getAllInteraction() {
        return interactionService.getAllInteraction();
    }

    @PutMapping("/{id}")
    public Interaction updateInteraction(@PathVariable Long id, @RequestBody Interaction interaction) {
       return interactionService.updateInteraction(interaction);
    }

    @DeleteMapping("/{id}")
    public void deleteInteraction(@PathVariable Long id) {
        interactionService.deleteInteraction(id);
    }
}
