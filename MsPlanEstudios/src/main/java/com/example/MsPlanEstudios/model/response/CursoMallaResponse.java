package com.example.MsPlanEstudios.model.response;

import java.util.List;

import lombok.Data;

@Data
public class CursoMallaResponse {
    private Integer id; // id del curso (idCurso)
    private Integer creditos;
    private Integer horasTeoricas;
    private Integer horasPracticas;
    private Integer ordenEnCiclo;
    private List<Integer> prerrequisitos; // lista de ids de cursos prerequisito
}