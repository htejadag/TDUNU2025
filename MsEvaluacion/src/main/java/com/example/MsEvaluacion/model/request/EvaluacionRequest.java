package com.example.MsEvaluacion.model.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EvaluacionRequest {
    
    private String id;
    
    private String idAlumno;
    
    private String idCicloAcademico;
    
    private String idCursoDetalle;

    private LocalDateTime fechaEvaluacion;
}
