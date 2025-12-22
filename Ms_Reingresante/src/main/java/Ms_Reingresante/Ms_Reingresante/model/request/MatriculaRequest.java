package Ms_Reingresante.Ms_Reingresante.model.request;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MatriculaRequest {
    private Integer idProceso;
    private Integer idResolucion;
    private LocalDate matFecha;
    private String realizadoPor;
    
    // Campos de Auditoría
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;
}