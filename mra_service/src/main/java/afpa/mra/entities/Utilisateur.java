package afpa.mra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "utilisateurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String mdp;

    @Column(length=2048)
    private String description;

    @Column(nullable = false)
    private String etatInscription;

    @Column(nullable = true)
    private  String urlPhoto;

    @Column(nullable = true)
    private String urlBanniere;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "geolocalisation_id", nullable = true)
    private Geolocalisation geolocalisation;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date dateCreation;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date dateModification;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Embauche> embauches;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Stage> stages;
    
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Suivi> suivis;
    
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Interaction> interactions;
    
    @OneToMany(mappedBy = "expediteur", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Message> messagesExpediteur;
    
    @OneToMany(mappedBy = "destinataire", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Message> messagesDestinataire;
    
    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<Publication> publications;
    
}
