package afpa.mra.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Table(name = "embauches")
@Data
public class Embauche {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JoinColumn(nullable = false)
    @ManyToOne
    private Utilisateur utilisateur;
    
    @JoinColumn(nullable = false)
    @ManyToOne
    private Entreprise entreprise;

    @Column(nullable = false)
    private Date dateDebut;

    @Column
    private Date dateFin;

}
