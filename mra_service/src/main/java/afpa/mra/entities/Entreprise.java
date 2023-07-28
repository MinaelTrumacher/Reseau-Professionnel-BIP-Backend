package afpa.mra.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "entreprises")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Entreprise {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String raisonSociale;
    
    @Column(nullable = false)
    private Long siret;
}
