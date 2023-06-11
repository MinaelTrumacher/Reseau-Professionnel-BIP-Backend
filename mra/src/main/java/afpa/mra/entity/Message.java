package afpa.mra.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "message")
public class Message {

    @EmbeddedId
    private MessageId id;

    @Column(length = 1000)
    private String contenu;

    @Column(name = "url_image", length = 1000)
    private String urlImage;

}

