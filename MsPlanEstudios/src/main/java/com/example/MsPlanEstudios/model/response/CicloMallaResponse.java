package com.example.MsPlanEstudios.model.response;

import java.util.List;

import lombok.Data;

@Data
public class CicloMallaResponse {
    private Integer id;
    private String nombre; // descripcion del catalogo (ciclo)
    private List<CursoMallaResponse> cursos;
}