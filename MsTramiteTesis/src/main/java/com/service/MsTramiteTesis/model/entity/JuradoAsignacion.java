package com.service.MsTramiteTesis.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "jurado_asignacion", indexes = {
        @Index(name = "idx_jurado_proyecto", columnList = "id_proyecto"),
        @Index(name = "idx_jurado_docente", columnList = "id_docente_ext")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uq_jurado_proyecto_docente", columnNames = { "id_proyecto", "id_docente_ext" }),
        @UniqueConstraint(name = "uq_jurado_proyecto_rol", columnNames = { "id_proyecto", "rol_jurado_codigo" })
})
public class JuradoAsignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Long idAsignacion;

    @Column(name = "id_proyecto", nullable = false)
    private Long idProyecto;

    @Column(name = "id_docente_ext", nullable = false)
    private Long idDocenteExt;

    @Column(name = "rol_jurado_codigo", nullable = false, length = 60)
    private String rolJuradoCodigo;

    @Column(name = "habilitado", nullable = false)
    private Boolean habilitado = true;

    // Constructors
    public JuradoAsignacion() {
        this.habilitado = true;
    }

    public JuradoAsignacion(Long idProyecto, Long idDocenteExt, String rolJuradoCodigo) {
        this.idProyecto = idProyecto;
        this.idDocenteExt = idDocenteExt;
        this.rolJuradoCodigo = rolJuradoCodigo;
        this.habilitado = true;
    }

    // Getters and Setters
    public Long getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Long idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Long getIdDocenteExt() {
        return idDocenteExt;
    }

    public void setIdDocenteExt(Long idDocenteExt) {
        this.idDocenteExt = idDocenteExt;
    }

    public String getRolJuradoCodigo() {
        return rolJuradoCodigo;
    }

    public void setRolJuradoCodigo(String rolJuradoCodigo) {
        this.rolJuradoCodigo = rolJuradoCodigo;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @PrePersist
    protected void onCreate() {
        if (habilitado == null) {
            habilitado = true;
        }
    }
}
