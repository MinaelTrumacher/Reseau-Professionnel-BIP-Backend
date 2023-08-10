package afpa.mra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import afpa.mra.entities.Publication;
import afpa.mra.entities.TypePublication;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {
	
    List<Publication> findByUtilisateur_Id(Long userId);
    
    List<Publication> findByOrderByDateCreationDesc();
    
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
    
    /* GET PUBLICATION ET LES INTERACTION PAR UTILISATEUR */
    
    @Query("SELECT DISTINCT p "
	       + "FROM Publication p "
	       + "LEFT JOIN FETCH p.interactions i "
	       + "WHERE i.utilisateur.id = :userId "
	       + "ORDER BY p.dateCreation ASC")
	List<Publication> getPublicationWithInteractionByUser(@Param("userId") Long userId);
    
    /* GET PUBLICATION FAVORIS PAR UTILISATEUR */
    
    @Query("SELECT DISTINCT p "
            + "FROM Interaction i "
            + "INNER JOIN i.publication p "
            + "INNER JOIN p.utilisateur u "
            + "WHERE i.typeInteraction = 'favoris' "
            + "ORDER BY p.dateCreation")
    List<Publication> getPublicationWithFavoris(@Param("userId") Long userId);
}