package afpa.mra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import afpa.mra.entities.Publication;

import java.util.List;


@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {
    List<Publication> findByUtilisateur_Id(Long userId);
}