package unu.td.MsAcademico.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import unu.td.MsAcademico.utils.Messages;

@Data
public class FacultadRequest {

    @NotBlank(message = Messages.REQUIRED_NOMBRE)
    @Length(message = Messages.MAX_LENGHT_NOMBRE)
    private String nombre;

}
