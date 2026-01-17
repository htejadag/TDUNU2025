package com.example.MsEvaluacion.model.events;

import lombok.Data;

@Data
public class EvaluacionFinalRegistradaEvent {
    private String eventId; // UUID del evento
    private String action; // FINALIZADA

    private Integer idCursoDetalle; // CLAVE
    private Double promedioFinal; // contexto mínimo

    private Long timestamp;
}
