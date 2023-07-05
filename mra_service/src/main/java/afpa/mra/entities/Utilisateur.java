package afpa.mra.entities;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "utilisateurs")
@Getter
@Setter
@Transactional
public class Utilisateur {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String prenom;
    
    @Column(nullable = false)
    private Role role;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String mdp;
    
    @Column
    private String description;
    
    @Column(nullable = false)
    private String etatInscription;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Geolocalisation geolocalisation;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date dateCreation;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date dateModification;
}
