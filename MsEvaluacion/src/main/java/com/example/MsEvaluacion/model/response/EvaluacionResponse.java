package com.example.MsEvaluacion.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EvaluacionResponse implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String id;
    
    private String idAlumno;
    
    private String idCicloAcademico;
    
    private String idCursoDetalle;

    // Promedio final: (promedioPracticas + notaExamenParcial + notaExamenFinal) / 3
    private Double promedioFinal;

    private LocalDateTime fechaEvaluacion;

    private String usuarioCreacion;

    private String usuarioModificacion;
}
