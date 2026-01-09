package msposgrado.Model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum que representa los posibles dictámenes que puede emitir un jurado
 * sobre una revisión de tesis o proyecto.
 */
@Schema(description = "Posibles dictámenes que puede emitir un jurado sobre una revisión")
public enum Dictamen {

    @Schema(description = "La revisión presenta observaciones y no cumple completamente con los criterios")
    OBSERVADO,

    @Schema(description = "La revisión ha sido aprobada y cumple con los criterios establecidos")
    APROBADO
}