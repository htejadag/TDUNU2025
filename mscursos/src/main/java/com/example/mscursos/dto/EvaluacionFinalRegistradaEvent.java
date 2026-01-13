package com.example.mscursos.dto;

import lombok.Data;

@Data
public class EvaluacionFinalRegistradaEvent {

    private String eventId;
    private String action;           // FINALIZADA
    private Integer idCursoDetalle;
    private Double promedioFinal;
    private Long timestamp;
}