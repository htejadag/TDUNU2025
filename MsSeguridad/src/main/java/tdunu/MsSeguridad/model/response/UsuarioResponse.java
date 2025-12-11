package tdunu.MsSeguridad.model.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import java.util.Set;

@Data
@JsonPropertyOrder({
    "idUsuario",
    "codUsuario",
    "nombre",
    "apellido",
    "correo",
    "celular",
    "contrasena",
    "estado"
})
public class UsuarioResponse {

    private Long idUsuario;
    private String codUsuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String celular;
    private String contrasena;
    private Integer estado;
    private Set<String> roles;
}
