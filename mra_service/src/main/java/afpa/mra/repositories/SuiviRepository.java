package afpa.mra.repositories;

import afpa.mra.entities.Suivi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuiviRepository extends JpaRepository<Suivi, Long> {

}
