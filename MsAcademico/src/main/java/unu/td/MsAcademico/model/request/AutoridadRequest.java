package unu.td.MsAcademico.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import unu.td.MsAcademico.utils.Messages;

import java.time.LocalDate;

@Data
public class AutoridadRequest {

    @NotBlank(message = Messages.REQUIRED_ID_USUARIO)
    private String idUsuario;

    @NotNull(message = Messages.REQUIRED_ID_TIPO_AUTORIDAD)
    private Integer idTipoAutoridad;

    @NotNull(message = Messages.REQUIRED_ID_ENTIDAD)
    private Integer idEntidad;

    @NotNull(message = Messages.REQUIRED_ID_TIPO_ENTIDAD)
    private Integer idTipoEntidad;

    @NotNull(message = Messages.REQUIRED_FECHA_INICIO)
    private LocalDate fechaInicio;

    private LocalDate fechaFin;
}
