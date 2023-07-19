package afpa.mra.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "interactions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Interaction {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeInteraction typeInteraction;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date dateInteraction;

    @ManyToOne()
    @JoinColumn(nullable=false, name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(nullable=false, name = "publication_id")
    private Publication publication;
}