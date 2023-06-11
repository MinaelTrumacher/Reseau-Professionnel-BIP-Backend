package afpa.mra.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;

    @Column(unique = true)
    private String adresseEmail;

    @Column(length = 1000)
    private String motDePasse;

    private String nom;

    private String prenom;

    @Column(length = 1000)
    private String urlImage;

    @Column(length = 3)
    private int age;

    @Column(length = 10)
    private LocalDate dateNaissance;

    private String promotion;

    private LocalDateTime derniereConnexion;

    private LocalDate dateInscription;

    private String etat;

    private String role;

    @PrePersist
    public void prePersist() {
        if (dateInscription == null) {
            dateInscription = LocalDate.now();
        }
    }

}
