package afpa.mra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import afpa.mra.entities.Publication;
import afpa.mra.entities.TypePublication;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {
	
    List<Publication> findByUtilisateur_Id(Long userId);
    
    /* FILTRE */
    @Query("SELECT DISTINCT p "
            + "FROM Publication p "
            + "WHERE (LOWER(p.title) LIKE %:keywords% "
            + "OR LOWER(p.contenu) LIKE %:keywords%) "
            + "AND p.categorie IN :types")
    List<Publication> findWithFiltre(String keywords, List<TypePublication> types);
    
    @Query("SELECT DISTINCT p "
            + "FROM Publication p "
            + "WHERE (LOWER(p.title) LIKE %:keywords% "
            + "OR LOWER(p.contenu) LIKE %:keywords%) "
            + "AND p.categorie IN :types "
            + "AND p.geolocalisation.id IN :villes")
    List<Publication> findWithFiltre(String keywords, List<TypePublication> types, List<Long> villes);

    @Query("SELECT DISTINCT p "
            + "FROM Publication p "
            + "WHERE p.categorie IN :types")
    List<Publication> findWithFiltre(List<TypePublication> types);
    
    @Query("SELECT DISTINCT p "
            + "FROM Publication p "
            + "WHERE p.categorie IN :types "
            + "AND p.geolocalisation.id IN :villes")
	List<Publication> findWithFiltre(List<TypePublication> types, List<Long> villes);
    
    /* GET PUBLICATION AND INTERACTION BY USER */
    
    @Query("SELECT DISTINCT p "
	       + "FROM Publication p "
	       + "LEFT JOIN FETCH p.interactions i "
	       + "WHERE i.utilisateur.id = :userId "
	       + "ORDER BY p.dateCreation")
	List<Publication> getPublicationWithInteractionByUser(Long userId);
}