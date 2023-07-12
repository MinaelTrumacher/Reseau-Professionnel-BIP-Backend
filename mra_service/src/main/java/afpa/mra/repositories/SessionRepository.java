package afpa.mra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import afpa.mra.entities.Session;


@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

}
