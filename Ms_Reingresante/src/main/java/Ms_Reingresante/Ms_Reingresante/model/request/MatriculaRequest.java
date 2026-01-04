package Ms_Reingresante.Ms_Reingresante.model.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MatriculaRequest {
    private Integer idProceso;
    private Integer idResolucion;
    private LocalDate matFecha;
    private String realizadoPor;
    
    // Campos de Auditoría
    private LocalDate fechaCreacion;
    private String usuarioCreacion;
    private LocalDate fechaModificacion;
    private String usuarioModificacion;
}