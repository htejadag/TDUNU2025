package com.MsExamen.dto;

import com.MsExamen.model.Pregunta.TipoPregunta;
import lombok.Data;

@Data
public class PreguntaDTO {
    private Integer idPregunta;
    private Integer idExamen;
    private String preguntaTexto;
    private TipoPregunta tipo;
    private java.time.LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private java.time.LocalDateTime fechaModificacion;
    private String usuarioModificacion;
}
