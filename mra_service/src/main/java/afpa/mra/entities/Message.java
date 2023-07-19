package afpa.mra.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "messages")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Message {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    
    @Column(nullable = false)
    private String contenu;
    
    @Column(nullable = false)
    private Boolean vu;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Utilisateur expediteur;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Utilisateur destinataire;
    
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date dateEnvoi;
}
