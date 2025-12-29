package tdunu.MsSeguridad.model.response;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private String usuario;
    private String expiracion;
}
