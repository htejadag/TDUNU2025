package unu.td.MsAcademico.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import unu.td.MsAcademico.utils.Messages;

@Data
public class CatalogoRequest {

    @NotBlank(message = Messages.REQUIRED_CATEGORIA)
    private String categoria;

    @NotBlank(message = Messages.REQUIRED_NOMBRE)
    private String nombre;

    @Size(max = 10, message = Messages.MAX_LENGHT_ABREVIATURA)
    private String abreviatura;

    @Min(value = 1, message = Messages.MIN_VALOR)
    private Integer valor;

    @Min(value = 1, message = Messages.MIN_ORDEN)
    private Integer orden;
}