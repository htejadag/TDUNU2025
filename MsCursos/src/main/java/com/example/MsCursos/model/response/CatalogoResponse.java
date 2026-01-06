package com.example.MsCursos.model.response;

import lombok.Data;

@Data
public class CatalogoResponse {
    private Integer id;
    private String categoria;
    private String nombre;
    private Integer orden;
    private String idPadre;
    private Boolean estado;
}
