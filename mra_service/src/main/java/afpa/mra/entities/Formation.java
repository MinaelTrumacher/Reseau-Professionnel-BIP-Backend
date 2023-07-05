package afpa.mra.entities;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "formations")
@Getter
@Setter
@Transactional
public class Formation {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    
    @Column(nullable = false)
    private String codeRncp;

    @Column(nullable = false)
    private String titre;
}
