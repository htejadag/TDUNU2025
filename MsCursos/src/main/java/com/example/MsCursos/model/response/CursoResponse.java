package com.example.mscursos.model.response;

import lombok.Data;

@Data
public class CursoResponse {
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
