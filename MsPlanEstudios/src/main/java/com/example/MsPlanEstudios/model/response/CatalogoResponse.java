package com.example.MsPlanEstudios.model.response;

import lombok.Data;

@Data
public class CatalogoResponse {
    private Integer id;
    private String categoria;
    private Integer codigo;
    private String descripcion;
    private String abreviatura;
    private Boolean estado;
}
