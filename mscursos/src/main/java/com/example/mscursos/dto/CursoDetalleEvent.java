package com.example.mscursos.dto;

import lombok.Data;

@Data
public class CursoDetalleEvent {
    private String eventId; // UUID
    private String action; // CREATED / UPDATED / DELETED

    private Integer idDetalleCurso;

    private Integer idCurso;
    private String cursoNombre; // (sí puedes, porque tienes CursoModel)
    private String cursoCodigo;

    private Integer idDocente; // solo ID (no tienes datos del docente)
    private Integer idTipoCurso;
    private Integer idSemestre;

    private Boolean estado;
    private Long timestamp;
}
