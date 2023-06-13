package afpa.mra.repository;

import afpa.mra.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    @Query(value = "SELECT * FROM Utilisateur WHERE adresse_email = :email", nativeQuery = true)
    public Utilisateur getByEmail(@Param("email") String email);

}
