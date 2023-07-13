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
    
    private String latitude;
    

    private String longitude;


    private String codePostal;

 
    private String region;


    private String ville;


}
