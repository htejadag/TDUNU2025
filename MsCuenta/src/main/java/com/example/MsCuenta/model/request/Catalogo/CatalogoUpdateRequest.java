package com.example.MsCuenta.model.request.Catalogo;

import lombok.Data;

@Data
public class CatalogoUpdateRequest {

    private String descripcion;

    private boolean activo;
    
    private Integer usuarioModificacion;

    
}
