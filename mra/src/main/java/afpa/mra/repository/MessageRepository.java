package afpa.mra.repository;

import afpa.mra.entity.Message;
import afpa.mra.entity.MessageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, MessageId> {

    @Query(value = "SELECT * FROM MESSAGE WHERE ID_EMETTEUR = :id UNION SELECT * FROM MESSAGE WHERE ID_RECEPTEUR = :id", nativeQuery = true)
    public List<Message> findByUtilisateur(@Param("id") Integer id);

    @Query(value = "SELECT * FROM MESSAGE WHERE ID_EMETTEUR = :idU AND ID_RECEPTEUR = :idI UNION SELECT * FROM MESSAGE WHERE ID_RECEPTEUR = :idU AND ID_EMETTEUR = :idI", nativeQuery = true)
    public List<Message> findByUtilisateurAndInterlocauteur(@Param("idU") Integer idU, @Param("idI") Integer idI);

}
