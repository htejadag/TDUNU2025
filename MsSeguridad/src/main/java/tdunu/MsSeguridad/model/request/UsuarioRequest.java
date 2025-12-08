package tdunu.MsSeguridad.model.request;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String codUsuario;
    private String contrasena;  
    private Integer estado;
    private String nombre;
    private String apellido;
    private String correo;
    private String celular;
        
}
