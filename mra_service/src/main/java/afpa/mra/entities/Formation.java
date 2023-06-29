package afpa.mra.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "formations")
public class Formation {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    
    @Column(nullable = false)
    private Titre titre;
    
    @Column(nullable = false)
    private String codeMcp;
}
