package afpa.mra.repository;

import afpa.mra.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
