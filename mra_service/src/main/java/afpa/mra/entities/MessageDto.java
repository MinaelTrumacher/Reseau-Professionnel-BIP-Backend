package afpa.mra.entities;

import afpa.mra.ExceptionPersonnalisee.UserNotFoundException;
import afpa.mra.repositories.UtilisateurRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Optional;


@AllArgsConstructor
@Data
public class MessageDto {

    private Long id;

    private String contenu;

    private Boolean vu;

    private Long expediteur_id;

    private Long destination_id;

    private String nomExpediteur;

    private String nomDestination;

    private Long supprimerParUserId;

    private Date dateEnvoi;

    public static MessageDto ConvertToMessageDto(Message message) {
        return new MessageDto(
                message.getId(),
                message.getContenu(),
                message.getVu(),
                message.getExpediteur().getId(),
                message.getDestinataire().getId(),
                message.getExpediteur().getNom().concat(" ").concat(message.getExpediteur().getPrenom()),
                message.getDestinataire().getNom().concat(" ").concat(message.getDestinataire().getPrenom()),
                message.getSupprimerParUserId(),
                message.getDateEnvoi()
        );
    }

    public Message ConvertToMessage(UtilisateurRepository utilisateurRepository) throws UserNotFoundException {
        Message message = new Message();
        Optional<Utilisateur> optionalDestinataire = utilisateurRepository.findById(this.getDestination_id());
        Optional<Utilisateur> optionalExpediteur = utilisateurRepository.findById(this.getDestination_id());
        if (optionalDestinataire.isPresent() && optionalExpediteur.isPresent()) {
            message.setExpediteur(utilisateurRepository.findById(this.getExpediteur_id()).get());
            message.setDestinataire(utilisateurRepository.findById(this.getDestination_id()).get());
        }else {
            throw new UserNotFoundException("Le destinataire ou l'expéditeur n'as pas étais trouvé dans la BDD" );
        }
        message.setVu(this.getVu());
        message.setDateEnvoi(this.getDateEnvoi());
        message.setContenu(this.getContenu());
        message.setSupprimerParUserId(this.getSupprimerParUserId());
        return message;
    }
}
