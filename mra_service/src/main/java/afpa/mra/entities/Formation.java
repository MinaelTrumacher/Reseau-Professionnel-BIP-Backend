package afpa.mra.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "formations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Formation {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Titre titre;
    
    @Column(nullable = false)
    private String codeRncp;
}
