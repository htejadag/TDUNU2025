package com.example.mscursos.dto;

import lombok.Data;

@Data
public class CursoDetalleEvent {

    private String eventId; // UUID del evento
    private String action; // CREATED | UPDATED | DELETED

    private Integer idDetalleCurso;
    private Integer idCurso;
    private Integer idSemestre;

    private Boolean estado;
    private Long timestamp;
}
