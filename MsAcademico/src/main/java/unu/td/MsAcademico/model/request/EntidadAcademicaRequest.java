package unu.td.MsAcademico.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import unu.td.MsAcademico.utils.Messages;

import java.time.LocalDate;

@Data
public abstract class EntidadAcademicaRequest {

    @NotBlank(message = Messages.REQUIRED_NOMBRE)
    @Size(max = 100, message = Messages.MAX_LENGHT_NOMBRE)
    private String nombre;

    private String descripcion;

    @NotNull(message = Messages.REQUIRED_FECHA_FUNDACION)
    @PastOrPresent(message = Messages.INVALID_FECHA_FUNDACION)
    private LocalDate fechaFundacion;
}
