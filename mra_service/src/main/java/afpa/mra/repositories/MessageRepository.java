package afpa.mra.repositories;

import afpa.mra.entities.Message;
import afpa.mra.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    /**
     * @param userId id de l'utilisateur
     * @return List<Message> liste des derniers messages échangés entre l'utilisateur et autres utilisateurs
     */

    @Query("SELECT MAX(m.dateEnvoi) AS maxDateEnvoi, m.contenu, m.id, m.vu, m.destinataire.id, m.expediteur.id, m.supprimerParUserId " +

            "FROM Message m " +
            "INNER JOIN (" +
            "   SELECT MAX(dateEnvoi) AS maxDateEnvoi, " +
            "   LEAST(destinataire.id, expediteur.id) AS minId, " +
            "   GREATEST(destinataire.id, expediteur.id) AS maxId " +
            "   FROM Message " +
            "   WHERE (expediteur.id = :userId OR destinataire.id = :userId) AND (supprimerParUserId <> :userId OR supprimerParUserId IS NULL)" +
            "   GROUP BY LEAST(destinataire.id, expediteur.id), GREATEST(destinataire.id, expediteur.id)" +
            ") sub " +
            "ON m.dateEnvoi = sub.maxDateEnvoi " +
            "AND (m.destinataire.id = sub.minId OR m.expediteur.id = sub.minId) " +
            "AND (m.destinataire.id = sub.maxId OR m.expediteur.id = sub.maxId) " +
            "WHERE m.expediteur.id = :userId OR m.destinataire.id = :userId " +
            "GROUP BY m.contenu, m.id, m.vu, m.destinataire.id, m.expediteur.id " +
            "ORDER BY MAX(m.dateEnvoi) DESC")
    List<Object[]> queryFindLastMessageSentOrRecevedByUser(@Param("userId") Long userId);

    default List<Message> findLastMessageSentOrRecevedByUser(Long userId) {
        List<Object[]> results = queryFindLastMessageSentOrRecevedByUser(userId);

        List<Message> messages = new ArrayList<>();

        for (Object[] result : results) {
            Date maxDateEnvoi = (Date) result[0];
            String contenu = (String) result[1];
            Long messageId = (Long) result[2];
            Boolean vu = (Boolean) result[3];
            Long destinataireId = (Long) result[4];
            Long expediteurId = (Long) result[5];
            Long supprimerParUserId = (Long) result[6];

            Utilisateur destinataire = new Utilisateur();
            destinataire.setId(destinataireId);

            Utilisateur expediteur = new Utilisateur();
            expediteur.setId(expediteurId);

            Message message = new Message();
            message.setDateEnvoi(maxDateEnvoi);
            message.setContenu(contenu);
            message.setId(messageId);
            message.setVu(vu);
            message.setDestinataire(destinataire);
            message.setExpediteur(expediteur);
            message.setSupprimerParUserId(supprimerParUserId);

            messages.add(message);
        }

        return messages;
    }
    /**
     * @param expediteur_id
     * @param destinataire_id
     * @return List<Message> liste des messages échangés entre deux utilisateurs spécifiques
     */
    List<Message> findByExpediteurIdAndDestinataireIdOrExpediteurIdAndDestinataireIdOrderByDateEnvoiAsc(Long expediteur_id, Long destinataire_id, Long destinataire_id1, Long expediteur_id1);

    void deleteByExpediteurIdAndDestinataireIdOrExpediteurIdAndDestinataireId(Long expediteur_id, Long destinataire_id, Long destinataire_id1, Long expediteur_id1);

    @Modifying
    @Query("UPDATE Message m SET m.supprimerParUserId = :userId " +
            "WHERE " +
            "((m.destinataire.id = :destinataireId AND m.expediteur.id = :expediteurId) OR " +
            "(m.destinataire.id = :expediteurId AND m.expediteur.id = :destinataireId))")
    void updateSupprimerParUserIdByDestinataireIdAndExpediteurIdOrExpediteurIdAndDestinataireId(@Param("destinataireId") Long destinataireId, @Param("expediteurId") Long expediteurId, @Param("userId") Long userId);
    @Modifying
    @Query("UPDATE Message m SET m.supprimerParUserId = null " +
            "WHERE " +
            "(m.expediteur.id = :expediteurId AND m.destinataire.id = :destinataireId AND m.supprimerParUserId = :expediteurId)" +
            " OR " +
            "(m.destinataire.id = :expediteurId AND m.expediteur.id = :destinataireId AND m.supprimerParUserId = :expediteurId)")
    void updateSupprimerParUserIdByExpediteurIdAndSupprimerParUserId(@Param("destinataireId") Long destinataireId, @Param("expediteurId") Long expediteurId);

    @Modifying
    @Query("UPDATE Message m SET m.vu = true " +
            "WHERE " +
            "((m.destinataire.id = :destinataireId AND m.expediteur.id = :expediteurId) OR " +
            "(m.destinataire.id = :expediteurId AND m.expediteur.id = :destinataireId)) and m.vu = false")
    void updateVuToTrueForAllMessageWhereDestinataireIdAndExpediteurIdOrExpediteurIdAndDestinataireId(@Param("destinataireId") Long destinataireId, @Param("expediteurId") Long expediteurId);
}