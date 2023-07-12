package afpa.mra.entities;

import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "session")
public class Session {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    
    @Column(nullable = false)
    private String nomPromo;
    
    @Column(nullable = false)
    private String centre;
    
    @Column(nullable = false)
    private Date dateDebut;
    
    @Column(nullable = false)
    private Date dateFin;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Utilisateur> utilisateurs;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Formation formation;
    
}
