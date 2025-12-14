package com.MsExamen.dto.request;

import lombok.Data;

@Data
public class CatalogoRequest {
    private String nombre;
    private String descripcion;
    private Boolean estado;
    private String usuarioCreacion;
    private String usuarioModificacion;
}
