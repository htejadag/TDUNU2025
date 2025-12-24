package com.service.MsTramiteTesis.model.dto;

import com.service.MsTramiteTesis.model.dto.external.DocenteDTO;
import com.service.MsTramiteTesis.model.dto.external.EstudianteDTO;

import java.time.OffsetDateTime;

/**
 * Response enriquecido con información completa de estudiantes y docentes
 */
public class ProyectoResponseEnriquecido {

    private Long idProyecto;

    // IDs externos
    private Long idEstudianteExt;
    private Long idAsesorExt;
    private Long idEspecialidadExt;

    // Información enriquecida del MS Personas
    private EstudianteDTO estudiante;
    private DocenteDTO asesor;

    // Datos del proyecto
    private String tituloProyecto;
    private String rutaPdfProyecto;
    private String estadoProyectoCodigo;
    private OffsetDateTime fechaRegistro;
    private OffsetDateTime fechaAprobacionFinal;

    // Constructors
    public ProyectoResponseEnriquecido() {
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

    public EstudianteDTO getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
    }

    public DocenteDTO getAsesor() {
        return asesor;
    }

    public void setAsesor(DocenteDTO asesor) {
        this.asesor = asesor;
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
