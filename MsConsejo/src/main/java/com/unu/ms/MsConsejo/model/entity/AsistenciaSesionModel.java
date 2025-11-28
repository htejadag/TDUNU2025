package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Timestamp;

import lombok.Data;

@Entity
@Table(name = "asistencia_sesion")
@Data
public class AsistenciaSesionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Integer idAsistencia;

    @ManyToOne
    @JoinColumn(name = "id_sesion")
    private SesionConsejoModel sesion;

    @ManyToOne
    @JoinColumn(name = "id_miembro")
    private MiembroConsejoModel miembro;

    @Column(name = "id_estado_asistencia")
    private Integer idEstadoAsistencia;

    @Column(name = "id_usuario_registro")
    private Integer usuarioRegistro;

    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;

}
