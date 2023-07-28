package afpa.mra.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "geolocalisations")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Geolocalisation {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String ville;
    
    @Column(nullable = false)
    private String region;
    
    private String latitude;
    
    private String longitude;

    @Column(nullable = false)
    private String codePostal;
}