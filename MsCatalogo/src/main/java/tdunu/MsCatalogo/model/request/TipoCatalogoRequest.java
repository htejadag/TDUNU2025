package tdunu.MsCatalogo.model.request;

import lombok.Data;

@Data
public class TipoCatalogoRequest {

    private String codigo;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private Integer usuarioCreacion;
}
