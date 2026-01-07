package com.example.mscursos.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoDetalleRequest {
    private Integer idDocente;
    private Integer idCurso;
    private Integer idTipoCurso;
    private Integer idSemestre;
}
