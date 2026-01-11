package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "asistencia_sesion")
@Data
@EqualsAndHashCode(callSuper = true)
public class AsistenciaSesionModel extends AuditoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Integer idAsistencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sesion", nullable = false)
    private SesionConsejoModel sesion;

    @ManyToOne
    @JoinColumn(name = "id_miembro", nullable = false)
    private MiembroConsejoModel miembro;

    @Column(name = "id_estado_asistencia", nullable = false)
    private Integer idEstadoAsistencia;

}
