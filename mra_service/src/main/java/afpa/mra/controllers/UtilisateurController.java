package afpa.mra.controllers;

import afpa.mra.entities.Geolocalisation;
import afpa.mra.entities.Message;
import afpa.mra.entities.Publication;
import afpa.mra.entities.Utilisateur;
import afpa.mra.repositories.EmbaucheRepository;
import afpa.mra.repositories.GeolocalisationRepository;
import afpa.mra.repositories.StageRepository;
import afpa.mra.repositories.UtilisateurRepository;
import afpa.mra.repositories.VerificationTokenRepository;
import afpa.mra.entities.VerificationToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private GeolocalisationRepository geolocalisationRepository;

    @Autowired
    private EmbaucheRepository embaucheRepository;

    @Autowired
    private StageRepository stageRepository;
    
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @PostMapping
    public ResponseEntity<Object> createUtilisateur(@RequestBody Utilisateur utilisateur) {
//        Optional<Geolocalisation> optionalGeolocalisation = geolocalisationRepository.findByLatitudeAndLongitude(utilisateur.getGeolocalisation().getLatitude(), utilisateur.getGeolocalisation().getLongitude());
        Optional<Geolocalisation> optionalGeolocalisation = geolocalisationRepository.findById(utilisateur.getGeolocalisation().getId());

        Utilisateur utilisateur1;

        if (optionalGeolocalisation.isEmpty()) {
            Geolocalisation geolocalisation = geolocalisationRepository.save(utilisateur.getGeolocalisation());
            utilisateur.setGeolocalisation(geolocalisation);
        } else {
            utilisateur.setGeolocalisation(optionalGeolocalisation.get());
        }

        utilisateur1 = utilisateurRepository.save(utilisateur);
        return new ResponseEntity<>(utilisateur1, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getUtilisateur(@PathVariable Long id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

        if (optionalUtilisateur.isEmpty()) {
            Map<String, Object> body = new HashMap<>();
            body.put("response", "Aucun utilisateur trouvé !");
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
    }

    @GetMapping(path = "filtreBynameOrPrenom/{nomOrPrenom}")
    public ResponseEntity<Object> getUtilisateurByFirstnameOrLastname(@PathVariable String nomOrPrenom) {
        List<Utilisateur> utilisateurs = utilisateurRepository.findByNomContainingOrPrenomContaining(nomOrPrenom, nomOrPrenom);
        if (utilisateurs.isEmpty()) {
            Map<String, Object> body = new HashMap<>();
            body.put("response", "Aucun utilisateur trouvé !");
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
        List<Map<String, Object>> list = new ArrayList<>();
        utilisateurs.forEach(utilisateur -> {
            Map<String, Object> utilisateurDto = new HashMap<>();
            utilisateurDto.put("id", utilisateur.getId());
            utilisateurDto.put("nomDestination", utilisateur.getNom().concat(" ").concat(utilisateur.getPrenom()) );
            list.add(utilisateurDto);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur updatedUtilisateur) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

        if (optionalUtilisateur.isEmpty()) {
            Map<String, Object> body = new HashMap<>();
            body.put("response", "Utilisateur non trouvé !");
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
        Optional<Geolocalisation> optionalGeolocalisation = geolocalisationRepository.findById(updatedUtilisateur.getGeolocalisation().getId());

        Utilisateur utilisateur = optionalUtilisateur.get();

        utilisateur.setNom(updatedUtilisateur.getNom());
        utilisateur.setPrenom(updatedUtilisateur.getPrenom());

        if (optionalGeolocalisation.isEmpty()) {
            Geolocalisation geolocalisation = geolocalisationRepository.save(updatedUtilisateur.getGeolocalisation());
            utilisateur.setGeolocalisation(geolocalisation);
        } else {
            utilisateur.setGeolocalisation(optionalGeolocalisation.get());
        }
        
        utilisateur = utilisateurRepository.save(utilisateur);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteUtilisateur(@PathVariable Long id) {
        Logger logger = LoggerFactory.getLogger(UtilisateurController.class);
        logger.info("Début de la méthode deleteUtilisateur avec l'ID : {}", id);
        try {
            // Trouver l'utilisateur à supprimer
            Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
            if (utilisateurOptional.isPresent()) {
                Utilisateur utilisateur = utilisateurOptional.get();

                // Récupérer le VerificationToken lié à cet utilisateur
                VerificationToken verificationToken = verificationTokenRepository.findByUtilisateur(utilisateur);

                if (verificationToken != null) {
                    // Supprimer le VerificationToken lié à cet utilisateur
                    verificationTokenRepository.delete(verificationToken);
                }
                
                // Définir les IDs des publications à null
                for (Publication publication : utilisateur.getPublications()) {
                	publication.setUtilisateur(null);
                }
                
                /*// Définir les IDs des messages destinataire à null
                for (Message message : utilisateur.getMessagesDestinataire()) {
                	message.setDestinataire(null);
                }*/

                // Ensuite, supprimer l'utilisateur de la table "utilisateurs"
                utilisateurRepository.deleteById(id);

                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // L'utilisateur avec l'ID donné n'a pas été trouvé
                return new ResponseEntity<>("Utilisateur non trouvé", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // En cas d'erreur, renvoyer une réponse d'erreur appropriée
            logger.error("Erreur lors de la suppression de l'utilisateur : {}", e.getMessage());
            return new ResponseEntity<>("Erreur lors de la suppression de l'utilisateur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
