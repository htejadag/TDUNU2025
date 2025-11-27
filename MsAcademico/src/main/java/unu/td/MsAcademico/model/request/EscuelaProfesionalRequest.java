package unu.td.MsAcademico.model.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import unu.td.MsAcademico.utils.Messages;

@Data
public class EscuelaProfesionalRequest {

    @NotNull(message = Messages.REQUIRED_ID_FACULTAD)
    private Integer idFacultad;

    @NotBlank(message = Messages.REQUIRED_NOMBRE)
    @Size(max = 20, message = Messages.MAX_LENGHT_NOMBRE)
    private String nombre;

    @Min(value = 1, message = Messages.MIN_DURACION)
    @Max(value = 10, message = Messages.MAX_DURACION)
    private Integer duracionCarrera;

}
