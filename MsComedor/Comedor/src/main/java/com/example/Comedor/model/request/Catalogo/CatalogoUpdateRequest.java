package com.example.Comedor.model.request.Catalogo;

import lombok.Data;

@Data
public class CatalogoUpdateRequest {

    private String tipo;

    private boolean activo;
    
    private Integer usuarioModificacion;

    
}

