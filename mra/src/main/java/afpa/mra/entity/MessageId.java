package afpa.mra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MessageId implements Serializable {
    @Column(name = "id_message")
    private int idMessage;

    @Column(name = "id_emetteur")
    private int idEmetteur;

    @Column(name = "id_recepteur")
    private int idRecepteur;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageId messageId = (MessageId) o;
        return idMessage == messageId.idMessage &&
                idEmetteur == messageId.idEmetteur &&
                idRecepteur == messageId.idRecepteur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMessage, idEmetteur, idRecepteur);
    }
}

