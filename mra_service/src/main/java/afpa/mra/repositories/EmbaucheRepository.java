package afpa.mra.repositories;

import afpa.mra.entities.Embauche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmbaucheRepository extends JpaRepository<Embauche, Long> {
}