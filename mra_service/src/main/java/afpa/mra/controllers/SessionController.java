package afpa.mra.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import afpa.mra.entities.Session;
import afpa.mra.entities.Formation;
import afpa.mra.repositories.SessionRepository;
import afpa.mra.repositories.FormationRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/session")
public class SessionController {
	
	@Autowired
	private SessionRepository sessionRepository ;
	@Autowired
	private FormationRepository formationRepository;
		
	@GetMapping("/{id}")
	public Optional<Session> getOneSession(@PathVariable Long id) {
		return sessionRepository.findById(id);
	}
	
    @GetMapping
    public List<Session> getAllSession() {
        return sessionRepository.findAll();
    }
    
	@PostMapping
	public Session addSession(@RequestBody Session session) {
		
		Formation formation = formationRepository.findById(session.getFormation().getId()).get();
		session.setFormation(formation);
		
		return sessionRepository.save(session);
	}
	
	@PutMapping
	public Session updateSession(@RequestBody Session session) {
		return sessionRepository.save(session);
	}
	
    @DeleteMapping("/{id}")
    public String deleteSession(@PathVariable Long id) {
         sessionRepository.deleteById(id);
         return "Session Supprimée";
    }


}
