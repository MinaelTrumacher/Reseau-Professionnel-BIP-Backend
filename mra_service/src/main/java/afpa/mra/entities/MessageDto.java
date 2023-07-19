package afpa.mra.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

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
                message.getDateEnvoi()
        );
    }
}
