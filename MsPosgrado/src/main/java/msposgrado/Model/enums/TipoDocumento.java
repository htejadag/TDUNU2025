package msposgrado.Model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum que representa los distintos tipos de documentos generados en el flujo de trámite.
 */
@Schema(description = "Tipos de documentos en el sistema")
public enum TipoDocumento {

    @Schema(description = "Documento de designación de asesor")
    DESIGNACION_ASESOR,

    @Schema(description = "Documento de designación de jurado")
    DESIGNACION_JURADO,

    @Schema(description = "Documento de aprobación del proyecto de tesis")
    APROBACION_PT,

    @Schema(description = "Memorando de informe final")
    MEMORANDO_IF,

    @Schema(description = "Resolución de expediente expeditivo")
    RESOLUCION_EXPEDITO,

    @Schema(description = "Resolución de sustentación")
    RESOLUCION_SUSTENTACION
}