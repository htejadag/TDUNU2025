package com.example.MsGeneral.Model.Response;

import lombok.Data;

@Data
public class CatalogoResponse {
    private String id;
    private String categoria;
    private Integer codigo;
    private String descripcion; 
    private String abreviatura;
    private Integer orden;
    private boolean activo;
}
