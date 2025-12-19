package com.unu.TDUNU2025.Msbiblioteca.model.request;

import lombok.Data;

@Data
public class CatalogoRequest {
    // No incluimos idCatalogo porque es autogenerado por la base de datos
    private String nombre;
    private String descripcion;
    private Integer estado; // 1: Activo, 0: Inactivo
}