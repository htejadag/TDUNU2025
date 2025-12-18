package com.unu.ms.MsConsejo.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "sesion_consejo")
@Data
public class SesionConsejoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private Integer idSesion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consejo", nullable = false)
    private ConsejoModel consejo;

    @Column(name = "numero_sesion", length = 50)
    private String numeroSesion;

    @Column(name = "nombre_sesion", length = 255)
    private String nombreSesion;

    @Column(name = "fecha_sesion")
    private LocalDate fechaSesion;

    @Column(name = "id_tipo_sesion")
    private Integer idTipoSesion;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "id_usuario_registro")
    private Integer usuarioRegistro;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AsistenciaSesionModel> asistencias;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }

}

