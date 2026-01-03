package unu.td.MsAcademico.model.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import unu.td.MsAcademico.utils.Messages;

@Data
public class EscuelaProfesionalRequest extends EntidadAcademicaRequest{

    @NotNull(message = Messages.REQUIRED_ID_FACULTAD)
    @Min(value = 1, message = Messages.MIN_ID)
    private Integer idFacultad;

    @Min(value = 1, message = Messages.MIN_DURACION)
    @Max(value = 10, message = Messages.MAX_DURACION)
    @NotNull(message = Messages.REQUIRED_DURACION_ESCUELA)
    private Integer duracionCarrera;

}
