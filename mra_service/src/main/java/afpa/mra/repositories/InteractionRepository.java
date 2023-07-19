package afpa.mra.repositories;

import afpa.mra.entities.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    Set<Interaction> findAllByPublicationId(Long publicationId);
}


