package Ms_Reingresante.Ms_Reingresante.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SolicitudReingresoResponse {
    
    public Integer idSolicitud;
    public Integer idProceso;
    public String solNumRegistro;
    public LocalDate solFechaPresentacion;
    public LocalDate solFechaRespuesta;
    public String solContenido;
    public String solEstado;
    public String solObservaciones;
    public Integer resolucionIdResolucion;
    public LocalDateTime fechaCreacion;
    public String usuarioCreacion;
    public LocalDateTime fechaModificacion;
    public String usuarioModificacion;
}