package Ms_Reingresante.Ms_Reingresante.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SolicitudReingresoResponse {
    
    private Integer idProceso;
    private String solNumRegistro;
    private LocalDate solFechaPresentacion;
    private LocalDate solFechaRespuesta;
    private String solContenido;
    private String solEstado;
    private String solObservaciones;
    private Integer resolucionIdResolucion;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;
}