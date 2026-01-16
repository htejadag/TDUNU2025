package unu.MsMatriculaCepre.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TA_MATRICULA")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula_id")
    private Integer matriculaId;

    @Column(name = "codigo", length = 30)
    private String codigo;

    @Column(name = "estudiante_id", nullable = false)
    private Integer estudianteId;

    @Column(name = "grupo_id", nullable = false)
    private Integer grupoId;

    @Column(name = "fecha_matricula")
    private LocalDateTime fechaMatricula;

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    @Column(name = "sistema_origen", length = 50)
    private String sistemaOrigen;

    // Campos de auditoría
    @Column(name = "au_fechacr")
    private LocalDateTime auFechacr;

    @Column(name = "au_fechamd")
    private LocalDateTime auFechamd;

    @Column(name = "au_usuariocr", length = 50)
    private String auUsuariocr;

    @Column(name = "au_usuariomd", length = 50)
    private String auUsuariomd;

    @PrePersist
    public void prePersist() {
        this.auFechacr = LocalDateTime.now();
        this.auFechamd = LocalDateTime.now();
        if (this.fechaMatricula == null) {
            this.fechaMatricula = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.auFechamd = LocalDateTime.now();
    }
}
