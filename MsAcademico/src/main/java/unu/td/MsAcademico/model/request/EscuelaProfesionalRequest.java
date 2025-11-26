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

    @Min(1)
    @Max(10)
    private Integer duracionCarrera;

}
