package com.example.mscursos.model.request;

import lombok.Data;

@Data
public class CursoDetalleRequest {
    private Integer idDocente;
    private Integer idCurso;
    private Integer idTipoCurso;
    private Integer idSemestre;
}
