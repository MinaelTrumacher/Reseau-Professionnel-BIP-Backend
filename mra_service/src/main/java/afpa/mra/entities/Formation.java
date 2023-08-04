package afpa.mra.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "formations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String codeRncp;
}
