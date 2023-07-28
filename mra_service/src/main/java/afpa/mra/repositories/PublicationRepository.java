package afpa.mra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import afpa.mra.entities.Publication;

import java.util.List;


@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {
    List<Publication> findByUtilisateur_Id(Long userId);

    @Query("SELECT DISTINCT p "
            + "FROM Publication p "
            + "WHERE (LOWER(p.title) LIKE %:keywords% "
            + "OR LOWER(p.contenu) LIKE %:keywords%) "
            + "AND p.categorie IN :types")
    List<Publication> findWithFiltre(String keywords, String[] types);

    @Query("SELECT DISTINCT p "
            + "FROM Publication p "
            + "WHERE p.categorie IN :types")
    List<Publication> findWithFiltre(String[] types);
}