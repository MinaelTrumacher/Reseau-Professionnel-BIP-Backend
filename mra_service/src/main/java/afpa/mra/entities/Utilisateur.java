package afpa.mra.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "utilisateurs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String mdp;
    
    @Column
    private String description;
    
    @Column(nullable = false)
    private String etatInscription;

    @Column(nullable = true)
    private  String urlPhoto;

    @Column(nullable = true)
    private String urlBanniere;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date dateCreation;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date dateModification;

    @ManyToOne
    private Geolocalisation geolocalisation;

    @OneToMany(mappedBy = "utilisateur")
    @JsonManagedReference// Annotation pour la sérialisation correcte des embauches
    private List<Embauche> embauches;

    @OneToMany(mappedBy = "utilisateur")
    @JsonManagedReference// Annotation pour la sérialisation correcte des stages
    private List<Stage> stages;
}
