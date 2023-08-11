package afpa.mra.entities;

import lombok.Data;

@Data
public class ModificationUtilisateurRequest {
    private String newUrlPhoto;
    private String newUrlBanniere;
    private String newDescription;
}