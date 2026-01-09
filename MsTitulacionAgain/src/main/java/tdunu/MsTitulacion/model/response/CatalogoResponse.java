package tdunu.MsTitulacion.model.response;

import lombok.Data;

@Data
public class CatalogoResponse {
    private Integer idCatalogo;
    private String codigo;
    private String valor;     // Lo que se muestra en el Dropdown
    private String categoria;
    private Boolean activo;
}   