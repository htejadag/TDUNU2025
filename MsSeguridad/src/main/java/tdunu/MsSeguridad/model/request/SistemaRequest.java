package tdunu.MsSeguridad.model.request;

import lombok.Data;

@Data
public class SistemaRequest {

    private String nombre;
    private String descripcion;
    private String urlBase;
    private Boolean estado;

}
