package unu.td.MsAcademico.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import unu.td.MsAcademico.utils.Messages;

import java.time.LocalDate;

@Data
public class AutoridadRequest {

    @NotBlank(message = Messages.REQUIRED_ID_USUARIO)
    private String idUsuario;

    @NotNull(message = Messages.REQUIRED_ID_TIPO_AUTORIDAD)
    @Min(value = 1, message = Messages.MIN_ID)
    private Integer idTipoAutoridad;

    @NotNull(message = Messages.REQUIRED_ID_TIPO_ENTIDAD)
    @Min(value = 1, message = Messages.MIN_ID)
    private Integer idTipoEntidad;

    @NotNull(message = Messages.REQUIRED_ID_ENTIDAD)
    @Min(value = 1, message = Messages.MIN_ID)
    private Integer idEntidad;

    @NotNull(message = Messages.REQUIRED_FECHA_INICIO)
    @PastOrPresent(message = Messages.INVALID_FECHA_INICIO)
    private LocalDate fechaInicio;

    @PastOrPresent(message = Messages.INVALID_FECHA_FIN)
    private LocalDate fechaFin;
}
