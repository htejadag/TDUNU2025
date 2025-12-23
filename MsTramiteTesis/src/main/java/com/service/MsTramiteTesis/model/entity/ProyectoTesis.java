package com.service.MsTramiteTesis.model.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "proyecto_tesis", indexes = {
        @Index(name = "idx_proyecto_estudiante_ext", columnList = "id_estudiante_ext"),
        @Index(name = "idx_proyecto_asesor_ext", columnList = "id_asesor_ext"),
        @Index(name = "idx_proyecto_estado_codigo", columnList = "estado_proyecto_codigo")
})
public class ProyectoTesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Long idProyecto;

    @Column(name = "id_estudiante_ext", nullable = false)
    private Long idEstudianteExt;

    @Column(name = "id_asesor_ext", nullable = false)
    private Long idAsesorExt;

    @Column(name = "id_especialidad_ext", nullable = false)
    private Long idEspecialidadExt;

    @Column(name = "titulo_proyecto", nullable = false, columnDefinition = "TEXT")
    private String tituloProyecto;

    @Column(name = "ruta_pdf_proyecto", columnDefinition = "TEXT")
    private String rutaPdfProyecto;

    @Column(name = "estado_proyecto_codigo", nullable = false, length = 60)
    private String estadoProyectoCodigo;

    @Column(name = "fecha_registro", nullable = false, columnDefinition = "TIMESTAMPTZ DEFAULT now()")
    private OffsetDateTime fechaRegistro;

    @Column(name = "fecha_aprobacion_final", columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime fechaAprobacionFinal;

    // Constructors
    public ProyectoTesis() {
        this.fechaRegistro = OffsetDateTime.now();
    }

    public ProyectoTesis(Long idEstudianteExt, Long idAsesorExt, Long idEspecialidadExt,
            String tituloProyecto, String estadoProyectoCodigo) {
        this.idEstudianteExt = idEstudianteExt;
        this.idAsesorExt = idAsesorExt;
        this.idEspecialidadExt = idEspecialidadExt;
        this.tituloProyecto = tituloProyecto;
        this.estadoProyectoCodigo = estadoProyectoCodigo;
        this.fechaRegistro = OffsetDateTime.now();
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

    @PrePersist
    protected void onCreate() {
        if (fechaRegistro == null) {
            fechaRegistro = OffsetDateTime.now();
        }
    }
}
