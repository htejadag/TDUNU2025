package unu.td.MsAcademico.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import unu.td.MsAcademico.utils.Messages;

@Data
public class FacultadRequest {

    @NotBlank(message = Messages.REQUIRED_NOMBRE)
    @Size(max = 20, message = Messages.MAX_LENGHT_NOMBRE)
    private String nombre;

}
