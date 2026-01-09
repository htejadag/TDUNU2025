package msposgrado.Model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum que representa los posibles estados de un proyecto de tesis.
 */
@Schema(description = "Posibles estados de un proyecto de tesis")
public enum EstadoProyecto {

    @Schema(description = "El proyecto no ha sido presentado")
    NO_PRESENTADO,

    @Schema(description = "El proyecto se encuentra en revisión por el jurado")
    EN_REVISION,

    @Schema(description = "El proyecto ha sido aprobado por el jurado")
    APROBADO
}