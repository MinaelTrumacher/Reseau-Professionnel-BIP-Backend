package afpa.mra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@AllArgsConstructor
@Data
public class MessageDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String contenu;

    @Column(nullable = false)
    private Boolean vu;

    @Column(nullable = false)
    private Long expediteur_id;

    @Column(nullable = false)
    private Long destination_id;

    @Column(nullable = false)
    private String nomExpediteur;

    @Column(nullable = false)
    private String nomDestination;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
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
