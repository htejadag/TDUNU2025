package com.example.MsPlanEstudios.model.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class CatalogoResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String categoria;
    private Integer codigo;
    private String descripcion;
    private String abreviatura;
    private Boolean estado;
}
