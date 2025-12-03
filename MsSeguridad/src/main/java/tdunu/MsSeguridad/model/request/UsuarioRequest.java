package tdunu.MsSeguridad.model.request;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String codUsuario;
    private String contrasena;  
    private Integer estado;
        
}
