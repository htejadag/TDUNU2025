package tdunu.MsSeguridad.model.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String codUsuario;
    private String contrasena;
}
