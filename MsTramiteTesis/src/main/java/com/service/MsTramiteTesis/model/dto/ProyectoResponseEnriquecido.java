package com.service.MsTramiteTesis.model.dto;

import com.service.MsTramiteTesis.model.dto.external.DocenteDTO;
import com.service.MsTramiteTesis.model.dto.external.EstudianteDTO;

import java.time.LocalDateTime;

/**
 * Response enriquecido con información completa de estudiantes y docentes
 */
public class ProyectoResponseEnriquecido {

    private Long idProyecto;

    // IDs
    private Integer idEstudiante;
    private Integer idAsesor;
    private Integer idLinea;

    // Información enriquecida del MS Personas
    private EstudianteDTO estudiante;
    private DocenteDTO asesor;

    // Datos del proyecto
    private String titulo;
    private String rutaArchivoProyecto;
    private String codigoSeguimiento;
    private Integer estadoProyectoCat;
    private LocalDateTime fechaRegistro;

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

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(Integer idAsesor) {
        this.idAsesor = idAsesor;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRutaArchivoProyecto() {
        return rutaArchivoProyecto;
    }

    public void setRutaArchivoProyecto(String rutaArchivoProyecto) {
        this.rutaArchivoProyecto = rutaArchivoProyecto;
    }

    public String getCodigoSeguimiento() {
        return codigoSeguimiento;
    }

    public void setCodigoSeguimiento(String codigoSeguimiento) {
        this.codigoSeguimiento = codigoSeguimiento;
    }

    public Integer getEstadoProyectoCat() {
        return estadoProyectoCat;
    }

    public void setEstadoProyectoCat(Integer estadoProyectoCat) {
        this.estadoProyectoCat = estadoProyectoCat;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
