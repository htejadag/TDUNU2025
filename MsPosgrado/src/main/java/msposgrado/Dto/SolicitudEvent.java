package msposgrado.Dto;

import java.time.LocalDateTime;

/**
 * Evento que representa la creación o modificación de una solicitud.
 * Este objeto es el contrato de datos que se envía a Kafka.
 */
public class SolicitudEvent {
    private Integer idSolicitud;
    private String tipoSolicitud;
    private String estadoSolicitud;
    private String descripcion;
    private LocalDateTime fechaRegistro;

    public SolicitudEvent() {
    }

    public SolicitudEvent(Integer idSolicitud, String tipoSolicitud, String estadoSolicitud, String descripcion,
            LocalDateTime fechaRegistro) {
        this.idSolicitud = idSolicitud;
        this.tipoSolicitud = tipoSolicitud;
        this.estadoSolicitud = estadoSolicitud;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
