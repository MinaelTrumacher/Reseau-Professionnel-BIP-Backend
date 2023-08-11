package afpa.mra.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "suivis")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Suivi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(nullable=false, name = "utilisateur_id")
    private Utilisateur utilisateur;
    
    @ManyToOne()
    private Session session;
}
