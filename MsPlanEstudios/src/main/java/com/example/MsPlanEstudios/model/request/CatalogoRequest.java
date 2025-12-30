package com.example.MsPlanEstudios.model.request;

import lombok.Data;

@Data
public class CatalogoRequest {
    private String categoria;
    private Integer codigo;
    private String descripcion;
    private String abreviatura;
}
