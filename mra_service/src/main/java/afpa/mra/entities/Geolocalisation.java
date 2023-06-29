package afpa.mra.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "geolocalisations")
@Data
public class Geolocalisation {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String ville;
    
    @Column(nullable = false)
    private String region;
    
    @Column(nullable = false)
    private String latitude;
    
    @Column(nullable = false)
    private String longitude;
}
