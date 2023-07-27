package afpa.mra.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import afpa.mra.entities.RazMdp;
import afpa.mra.entities.Utilisateur;

public interface RazMdpRepository extends JpaRepository<RazMdp, Long>{
    
    Optional<RazMdp> findByCodeAndUtilisateur(Integer code, Utilisateur utilisateur);
}
