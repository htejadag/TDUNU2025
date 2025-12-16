package com.example.MsCursos.model.request;

import lombok.Data;

@Data
public class CursoRequest {
    private Integer id;
    private String nombre;
    private String codigo;
    private Integer idCiclo;
    private Integer idCarrera;
    private Integer creditos;
    private Integer horasTeoricas;
    private Integer horasPracticas;
    private Boolean estado;
}
