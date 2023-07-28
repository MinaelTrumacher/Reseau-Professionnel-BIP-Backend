package afpa.mra.entities;

import lombok.Data;

@Data
public class RezMdpObject {
    private String email;
    private Integer code;
    private String password;
}
