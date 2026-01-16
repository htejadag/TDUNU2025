package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

/**
 * Entidad que representa un miembro del consejo.
 * 
 * Un miembro del consejo es una persona que forma parte de un consejo específico,
 * con un cargo definido (Director, Secretario, Tesorero, etc.) y un período de
 * participación definido. Cada miembro tiene registro de su asistencia a las
 * sesiones del consejo.
 * 
 * Propiedades principales:
 * - Referencia al consejo al que pertenece
 * - Identificador de la persona (referencia a sistema externo)
 * - Cargo o rol dentro del consejo
 * - Período de participación (fecha inicio y fin)
 * - Historial de asistencias a sesiones
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Entity
@Table(name = "miembro_consejo")
@Data
public class MiembroConsejoModel {

    /** Identificador único del miembro del consejo */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_miembro")
    private Integer idMiembro;

    /** Referencia al consejo al que pertenece este miembro */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consejo", nullable = false)
    private ConsejoModel consejo;

    /** Identificador de la persona en el sistema externo (referencia a base de datos de personas) */
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    /** Identificador del cargo del miembro (referencia al catálogo de cargos) */
    @Column(name = "id_cargo", nullable = false)
    private Integer idCargo;

    /** Fecha de inicio de la participación del miembro en el consejo */
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    /** Fecha de fin de la participación del miembro en el consejo (nulo si aún está activo) */
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    /** Historial de registros de asistencia a sesiones de este miembro */
    @OneToMany(mappedBy = "miembro")
    private List<AsistenciaSesionModel> asistencias;

}
