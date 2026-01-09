package msposgrado.Model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum que representa los posibles estados de una solicitud dentro del flujo de trámite.
 */
@Schema(description = "Posibles estados de una solicitud")
public enum EstadoSolicitud {

    @Schema(description = "La solicitud está pendiente de ser procesada")
    PENDIENTE,

    @Schema(description = "La solicitud está en proceso de revisión o evaluación")
    EN_PROCESO,

    @Schema(description = "La solicitud ha sido finalizada")
    FINALIZADO
}