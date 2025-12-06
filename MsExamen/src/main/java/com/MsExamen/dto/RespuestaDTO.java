package com.MsExamen.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RespuestaDTO {
    private Integer idRespuesta;
    private Integer idPregunta;
    private String respuestaTexto;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;
}
