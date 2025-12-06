package com.MsExamen.dto.request;

import com.MsExamen.model.Pregunta.TipoPregunta;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PreguntaRequest {
    @NotNull(message = "El ID del examen es obligatorio")
    private Integer idExamen;

    @NotBlank(message = "El texto de la pregunta es obligatorio")
    private String preguntaTexto;

    @NotNull(message = "El tipo de pregunta es obligatorio")
    private TipoPregunta tipo;

    private String usuarioCreacion;
    private String usuarioModificacion;
    private java.time.LocalDateTime fechaCreacion;
    private java.time.LocalDateTime fechaModificacion;
}
