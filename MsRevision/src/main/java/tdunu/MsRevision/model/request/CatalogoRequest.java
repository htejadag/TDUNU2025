package tdunu.MsRevision.model.request;

import lombok.Data;

@Data
public class CatalogoRequest {
    private String codigo;
    private String valor;
    private String categoria;
    private Boolean activo;
}