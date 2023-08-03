package afpa.mra.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordUpdate {
    private Long userId;
    private String ancienMdp;
    private String nouveauMdp;

}

