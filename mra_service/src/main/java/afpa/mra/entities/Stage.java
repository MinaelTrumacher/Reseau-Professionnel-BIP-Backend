package afpa.mra.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "stages")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "FAIL_ON_EMPTY_BEANS"})
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(nullable=false, name = "utilisateur_id")
    private Utilisateur utilisateur;
    
    @ManyToOne
    private Formation formation;
    
    @ManyToOne
    private Entreprise entreprise;

}
