package afpa.mra.repository;

import afpa.mra.entities.Embauche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmbaucheRepository extends JpaRepository<Embauche, Long> {
}