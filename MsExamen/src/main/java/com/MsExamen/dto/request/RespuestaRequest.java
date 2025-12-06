package com.MsExamen.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RespuestaRequest {
    @NotNull(message = "El ID de la pregunta es obligatorio")
    private Integer idPregunta;

    @NotBlank(message = "El texto de la respuesta es obligatorio")
    private String respuestaTexto;

    private String usuarioCreacion;
    private String usuarioModificacion;
    private java.time.LocalDateTime fechaCreacion;
    private java.time.LocalDateTime fechaModificacion;
}
