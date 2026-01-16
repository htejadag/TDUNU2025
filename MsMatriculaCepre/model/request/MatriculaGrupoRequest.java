package unu.MsMatriculaCepre.model.request;

import jakarta.validation.constraints.NotNull;

public class MatriculaGrupoRequest {

    @NotNull(message = "El id de la matrícula es obligatorio")
    private Integer matriculaId;

    @NotNull(message = "El id del grupo es obligatorio")
    private Long grupoId;

    private String observaciones;

    // 🔹 Constructor vacío
    public MatriculaGrupoRequest() {
    }

    // 🔹 Getters y Setters
    public Integer getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
