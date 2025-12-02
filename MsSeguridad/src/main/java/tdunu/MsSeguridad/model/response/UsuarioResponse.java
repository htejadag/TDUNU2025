package tdunu.MsSeguridad.model.response;

import lombok.Data;

@Data
public class UsuarioResponse {
    private Long idUsuario;
    private String codUsuario;
    private Integer estado;
}
