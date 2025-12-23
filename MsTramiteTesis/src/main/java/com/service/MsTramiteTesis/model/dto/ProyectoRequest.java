package com.service.MsTramiteTesis.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProyectoRequest {

    @NotNull(message = "El ID del asesor es obligatorio")
    private Long idAsesorExt;

    @NotNull(message = "El ID de la especialidad es obligatorio")
    private Long idEspecialidadExt;

    @NotBlank(message = "El título del proyecto es obligatorio")
    private String tituloProyecto;

    private String rutaPdfProyecto;

    // Constructors
    public ProyectoRequest() {
    }

    public ProyectoRequest(Long idAsesorExt, Long idEspecialidadExt, String tituloProyecto) {
        this.idAsesorExt = idAsesorExt;
        this.idEspecialidadExt = idEspecialidadExt;
        this.tituloProyecto = tituloProyecto;
    }

    // Getters and Setters
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
}
