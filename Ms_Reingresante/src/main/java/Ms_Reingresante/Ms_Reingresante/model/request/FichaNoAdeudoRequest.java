package Ms_Reingresante.Ms_Reingresante.model.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Nota: No se usa Lombok @Data aquí para seguir el estilo de tu ejemplo, 
// usando campos públicos.

public class FichaNoAdeudoRequest {

    // Clave Foránea: Necesario para vincular la ficha al proceso
    public Integer idProceso; 

    // Datos principales del registro (según el diagrama DB)
    public String fichaNumero;
    public LocalDate fechaEmision;
    public String emitidoPor;
    
    // Campos de Auditoría (siguiendo el patrón de tu ejemplo)
    public LocalDateTime fechaCreacion;
    public String usuarioCreacion;
    public LocalDateTime fechaModificacion;
    public String usuarioModificacion;

    // Nota: El id_Ficha (PK) se omite ya que es autogenerado.
}