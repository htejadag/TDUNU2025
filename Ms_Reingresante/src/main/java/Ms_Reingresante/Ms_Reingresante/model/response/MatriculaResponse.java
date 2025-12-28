package Ms_Reingresante.Ms_Reingresante.model.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MatriculaResponse {
    private Long idMatricula;
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