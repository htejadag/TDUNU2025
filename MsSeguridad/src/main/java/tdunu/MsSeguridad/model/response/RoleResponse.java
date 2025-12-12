package tdunu.MsSeguridad.model.response;

import lombok.Data;

import java.util.Set;

@Data
public class RoleResponse {

    private Long idRole;
    private String nombre;
    private String descripcion;
    private Set<String> permisos;
}
