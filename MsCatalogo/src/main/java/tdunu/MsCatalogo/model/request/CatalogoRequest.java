package tdunu.MsCatalogo.model.request;

import lombok.Data;

@Data
public class CatalogoRequest {

    private Integer tipoCatalogoId;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String valor;
    private Integer orden;
    private Boolean activo;
    private Integer usuarioCreacion;
}
