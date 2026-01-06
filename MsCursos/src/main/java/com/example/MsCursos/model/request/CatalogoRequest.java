package com.example.MsCursos.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CatalogoRequest {

    @NotBlank
    private String categoria;

    @NotBlank
    private String nombre;

    private Integer orden;

    @NotNull
    private String idPadre;

    private Boolean estado; // 
}
