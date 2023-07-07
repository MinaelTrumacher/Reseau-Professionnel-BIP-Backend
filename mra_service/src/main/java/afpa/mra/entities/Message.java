package afpa.mra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "message")
@Getter
@Setter
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
