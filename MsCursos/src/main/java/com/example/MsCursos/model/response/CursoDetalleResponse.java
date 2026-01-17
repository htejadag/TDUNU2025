package com.example.mscursos.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoDetalleResponse {
    private Integer id;
    private Integer idDocente;
    private Integer idCurso;
    private Integer idSemestre;
    private Integer idTipoCurso;
    private Boolean estado;
}
