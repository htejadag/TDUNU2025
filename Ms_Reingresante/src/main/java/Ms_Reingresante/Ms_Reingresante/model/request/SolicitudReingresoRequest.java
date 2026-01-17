package Ms_Reingresante.Ms_Reingresante.model.request;

import java.time.LocalDate;
import lombok.Data;

@Data

public class SolicitudReingresoRequest {
    
    private Integer idProceso;
    private String solNumRegistro;
    private LocalDate solFechaPresentacion;
    private LocalDate solFechaRespuesta;
    private String solContenido;
    private String solEstado;
    private String solObservaciones;
    private Integer resolucionIdResolucion;
    private LocalDate fechaCreacion;
    private String usuarioCreacion;
    private LocalDate fechaModificacion;
    private String usuarioModificacion;
}