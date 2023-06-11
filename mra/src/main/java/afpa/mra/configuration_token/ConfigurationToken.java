package afpa.mra.configuration_token;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Configuration
public class ConfigurationToken {

    private static final String URL_CVT = "http://localhost:8081/cvt/v1/verifier";

    public ResponseEntity verifier(String token, Object contenu) {
        if ("http://localhost:4200".equals(token)) {
        return new ResponseEntity(HttpStatus.OK);
        } else {
            try {
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", token);
                RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(URL_CVT));
                restTemplate.exchange(requestEntity, ResponseEntity.class);
                return new ResponseEntity<>(contenu, HttpStatus.OK);

            } catch (HttpClientErrorException.Unauthorized ex) {
                // Si l'authentification échoue, retourner le code de statut 401
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
    }

    public ResponseEntity verifier(String token) {
        if ("http://localhost:4200".equals(token)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            try {
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", token);
                RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(URL_CVT));
                restTemplate.exchange(requestEntity, ResponseEntity.class);
                return new ResponseEntity<>(HttpStatus.OK);

            } catch (HttpClientErrorException.Unauthorized ex) {
                // Si l'authentification échoue, retourner le code de statut 401
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
    }

}
