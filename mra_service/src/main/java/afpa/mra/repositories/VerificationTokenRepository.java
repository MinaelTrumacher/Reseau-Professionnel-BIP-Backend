package afpa.mra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import afpa.mra.entities.Utilisateur;
import afpa.mra.entities.VerificationToken;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
 
    VerificationToken findByToken(String token);

    VerificationToken findByUtilisateur(Utilisateur utilisateur);
}
