package afpa.mra.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "entreprises")
@Data
public class Entreprise {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long raisonSociale;
    
    @Column(nullable = false)
    private Long siret;
}
