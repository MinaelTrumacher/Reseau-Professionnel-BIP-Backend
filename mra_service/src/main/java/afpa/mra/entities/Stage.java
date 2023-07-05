package afpa.mra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "stage")
@Getter
@Setter
public class Stage {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
    
    @Column(nullable = false)
    private Date dateDebut; 
    
    @Column(nullable = false)
    private Date dateFin;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Utilisateur utilisateur;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Formation formation;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Entreprise entreprise;
}
