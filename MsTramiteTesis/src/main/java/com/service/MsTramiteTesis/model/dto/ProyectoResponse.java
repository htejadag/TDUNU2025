package com.service.MsTramiteTesis.model.dto;

import java.time.OffsetDateTime;

public class ProyectoResponse {

    private Long idProyecto;
    private Long idEstudianteExt;
    private Long idAsesorExt;
    private Long idEspecialidadExt;
    private String tituloProyecto;
    private String rutaPdfProyecto;
    private String estadoProyectoCodigo;
    private OffsetDateTime fechaRegistro;
    private OffsetDateTime fechaAprobacionFinal;

    // Constructors
    public ProyectoResponse() {
    }

    // Getters and Setters
    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Long getIdEstudianteExt() {
        return idEstudianteExt;
    }

    public void setIdEstudianteExt(Long idEstudianteExt) {
        this.idEstudianteExt = idEstudianteExt;
    }

    public Long getIdAsesorExt() {
        return idAsesorExt;
    }

    public void setIdAsesorExt(Long idAsesorExt) {
        this.idAsesorExt = idAsesorExt;
    }

    public Long getIdEspecialidadExt() {
        return idEspecialidadExt;
    }

    public void setIdEspecialidadExt(Long idEspecialidadExt) {
        this.idEspecialidadExt = idEspecialidadExt;
    }

    public String getTituloProyecto() {
        return tituloProyecto;
    }

    public void setTituloProyecto(String tituloProyecto) {
        this.tituloProyecto = tituloProyecto;
    }

    public String getRutaPdfProyecto() {
        return rutaPdfProyecto;
    }

    public void setRutaPdfProyecto(String rutaPdfProyecto) {
        this.rutaPdfProyecto = rutaPdfProyecto;
    }

    public String getEstadoProyectoCodigo() {
        return estadoProyectoCodigo;
    }

    public void setEstadoProyectoCodigo(String estadoProyectoCodigo) {
        this.estadoProyectoCodigo = estadoProyectoCodigo;
    }

    public OffsetDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(OffsetDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public OffsetDateTime getFechaAprobacionFinal() {
        return fechaAprobacionFinal;
    }

    public void setFechaAprobacionFinal(OffsetDateTime fechaAprobacionFinal) {
        this.fechaAprobacionFinal = fechaAprobacionFinal;
    }
}
