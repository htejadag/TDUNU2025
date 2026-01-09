package msposgrado.Model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum que representa los posibles estados de un informe final de tesis o proyecto.
 */
@Schema(description = "Posibles estados de un informe final de tesis o proyecto")
public enum EstadoInformeFinal {

    @Schema(description = "El informe final no ha sido presentado por el estudiante")
    NO_PRESENTADO,

    @Schema(description = "El informe final se encuentra en revisión por el jurado")
    EN_REVISION,

    @Schema(description = "El informe final ha sido aprobado por el jurado")
    APROBADO
}