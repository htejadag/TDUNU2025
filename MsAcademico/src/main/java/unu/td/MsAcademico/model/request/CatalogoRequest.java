package unu.td.msacademico.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import unu.td.msacademico.utils.Messages;

@Data
public class CatalogoRequest {

    @NotBlank(message = Messages.REQUIRED_CATEGORIA)
    private String categoria;

    @NotNull(message = Messages.REQUIRED_CODIGO)
    @Min(value = 1, message = Messages.MIN_ID)
    private Integer codigo;

    @NotBlank(message = Messages.REQUIRED_NOMBRE)
    private String nombre;

    private String abreviatura;

    @Min(value = 1, message = Messages.MIN_VALOR)
    private Integer valor;

    @Min(value = 1, message = Messages.MIN_ORDEN)
    private Integer orden;
}