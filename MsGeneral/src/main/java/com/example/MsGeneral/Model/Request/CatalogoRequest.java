package com.example.MsGeneral.Model.Request;

import lombok.Data;

@Data
public class CatalogoRequest {

    private String categoria;
    private String descripcion; 
    private String abreviatura;
    private boolean activo;
    
}
