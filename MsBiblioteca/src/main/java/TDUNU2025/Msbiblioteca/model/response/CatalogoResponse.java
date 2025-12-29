package com.unu.TDUNU2025.Msbiblioteca.model.response;

import lombok.Data;

@Data
public class CatalogoResponse {
    private Integer idCatalogo;
    private String nombre;
    private String descripcion;
    private Integer estado;
}