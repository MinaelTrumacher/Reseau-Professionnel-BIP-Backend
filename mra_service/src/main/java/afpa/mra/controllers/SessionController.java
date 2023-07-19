package afpa.mra.controllers;

import afpa.mra.entities.Formation;
import afpa.mra.entities.Session;
import afpa.mra.repositories.FormationRepository;
import afpa.mra.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sessions")
public class SessionController {
	
	@Autowired
	private SessionRepository sessionRepository ;
	@Autowired
	private FormationRepository formationRepository;

	@PostMapping
	public Session createSession(@RequestBody Session session) {

		Formation formation = formationRepository.findById(session.getFormation().getId()).get();
		session.setFormation(formation);

		return sessionRepository.save(session);
	}

	@GetMapping("/{id}")
	public Optional<Session> getSession(@PathVariable Long id) {
		return sessionRepository.findById(id);
	}
	
    @GetMapping
    public List<Session> getAllSession() {
        return sessionRepository.findAll();
    }
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Object> updateSession(@RequestBody Session session) {
		Optional<Formation> optionalFormation = formationRepository.findById(session.getFormation().getId());
		 Session session1;
		 if (optionalFormation.isEmpty()) {
			 Formation formation = formationRepository.save(session.getFormation());
			 session.setFormation(formation);
		 } else {
			 session.setFormation(optionalFormation.get());
		 }
		 session1 = sessionRepository.save(session);
		return new ResponseEntity<>(session1, HttpStatus.OK);
	}
	
    @DeleteMapping(path = "/{id}")
    public String deleteSession(@PathVariable Long id) {
         sessionRepository.deleteById(id);
         return "Session Supprimée";
    }


}
