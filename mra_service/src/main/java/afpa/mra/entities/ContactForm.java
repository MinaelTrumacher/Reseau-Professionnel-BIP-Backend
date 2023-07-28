package afpa.mra.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactForm {
    private String nom;
    private String prenom;
    private String email;
    private String message;
}
