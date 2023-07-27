package afpa.mra.repositories;

import afpa.mra.entities.Utilisateur;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    List<Utilisateur> findByNomContainingOrPrenomContaining(String nomOuPrenom, String nomOuPrenom1);

    Optional<Utilisateur> findByEmail(String email);

}
