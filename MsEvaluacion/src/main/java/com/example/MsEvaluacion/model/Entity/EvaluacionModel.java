package com.example.MsEvaluacion.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "evaluacion")
public class EvaluacionModel extends AuditoriaModel {

    @Id
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
