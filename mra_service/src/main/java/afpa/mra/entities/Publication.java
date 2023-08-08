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
@Table(name = "publications")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypePublication categorie;
    
    @Column(nullable = false,length=2048)
    private String contenu;
    
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date dateCreation;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date dateModification;

    @OneToMany(mappedBy = "publication",cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Interaction> interactions;

    @ManyToOne
    private Geolocalisation geolocalisation;

    @ManyToOne
    private Utilisateur utilisateur;

}
