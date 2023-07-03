package afpa.mra.repository;

import afpa.mra.entities.Embauche;
import afpa.mra.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
}
