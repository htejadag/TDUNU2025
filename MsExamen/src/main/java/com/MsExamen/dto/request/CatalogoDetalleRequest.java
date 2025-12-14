package com.MsExamen.dto.request;

import lombok.Data;

@Data
public class CatalogoDetalleRequest {
    private Integer idCatalogo;
    private String nombre;
    private String valor;
    private Boolean estado;
    private String usuarioCreacion;
    private String usuarioModificacion;
}
