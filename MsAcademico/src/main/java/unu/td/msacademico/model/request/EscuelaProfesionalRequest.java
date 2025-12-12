package unu.td.msacademico.model.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import unu.td.msacademico.utils.Messages;

@Data
public class EscuelaProfesionalRequest extends EntidadAcademicaRequest{

    @NotNull(message = Messages.REQUIRED_ID_FACULTAD)
    private Integer idFacultad;

    @Min(value = 1, message = Messages.MAX_DURACION)
    @Max(value = 10, message = Messages.MIN_DURACION)
    @NotNull(message = Messages.REQUIRED_DURACION_ESCUELA)
    private Integer duracionCarrera;

}
