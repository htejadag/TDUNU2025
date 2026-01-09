package msposgrado.Model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum que representa los tipos de revisión que se pueden realizar sobre una tesis.
 */
@Schema(description = "Tipos de revisión de una tesis")
public enum TipoRevision {

    @Schema(description = "Revisión del proyecto de tesis (PT)")
    REVISION_PT,

    @Schema(description = "Revisión del informe final (IF)")
    REVISION_IF
}