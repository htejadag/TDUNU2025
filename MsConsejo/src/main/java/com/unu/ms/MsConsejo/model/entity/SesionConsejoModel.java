package com.unu.ms.MsConsejo.model.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne
    @JoinColumn(name = "id_consejo")
    private ConsejoModel consejo;

    @Column(name = "numero_sesion")
    private String numeroSesion;

    @Column(name = "fecha_sesion")
    private LocalDate fechaSesion;

    @Column(name = "id_tipo_sesion")
    private Integer idTipoSesion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "id_usuario_registro")
    private Integer usuarioRegistro;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @OneToMany(mappedBy = "sesion")
    private List<AsistenciaSesionModel> asistencias;

}

