package afpa.mra.entities;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "entreprises")
@Data
@Getter
@Setter
@Transactional
public class Entreprise {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String raisonSociale;
    
    @Column(nullable = false)
    private String siret;
}
