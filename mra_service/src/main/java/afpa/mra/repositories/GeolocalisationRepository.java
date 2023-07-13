package afpa.mra.repositories;

import afpa.mra.entities.Geolocalisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeolocalisationRepository extends JpaRepository<Geolocalisation, Long> {

    /**
     * @param latitude
     * @param longitude
     * @return Optional<Geolocalisation>
     */
    Optional<Geolocalisation> findByLatitudeAndLongitude(String latitude, String longitude);

    List<Geolocalisation> findByCodePostal(String codePostal);

}
