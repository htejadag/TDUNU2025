package com.example.mscursos.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
