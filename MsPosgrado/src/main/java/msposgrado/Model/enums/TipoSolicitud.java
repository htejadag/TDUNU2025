package msposgrado.Model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum que representa los distintos tipos de solicitudes que pueden generarse
 * dentro del sistema académico para un expediente o tesis.
 */
@Schema(description = "Tipos de solicitudes disponibles para un expediente o tesis")
public enum TipoSolicitud {

    @Schema(description = "Solicitud para la designación de un asesor académico")
    DESIGNACION_ASESOR,

    @Schema(description = "Solicitud para la revisión del proyecto de tesis (PT)")
    REVISION_PT,

    @Schema(description = "Solicitud para la aprobación del proyecto de tesis (PT)")
    APROBACION_PT,

    @Schema(description = "Solicitud para la revisión del informe final (IF) de la tesis")
    REVISION_IF,

    @Schema(description = "Solicitud de emisión de un expediente o documento expreso")
    EXPEDITO,

    @Schema(description = "Solicitud para la sustentación del proyecto o tesis")
    SUSTENTACION
}