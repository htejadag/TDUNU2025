package tdunu.MsSeguridad.model.response;

import lombok.Data;

@Data
public class SistemaResponse {

    private Long idSistema;
    private String nombre;
    private String descripcion;
    private String urlBase;
    private Boolean estado;

}
