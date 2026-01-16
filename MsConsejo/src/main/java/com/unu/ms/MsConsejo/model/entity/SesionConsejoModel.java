package com.unu.ms.MsConsejo.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

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
import jakarta.persistence.Table;

import lombok.Data;

/**
 * Entidad que representa una sesión del consejo.
 * 
 * Una sesión es una reunión del consejo donde se lleva a cabo una serie de
 * actividades (votos, discusiones, etc.) y se registra la asistencia de los
 * miembros. Cada sesión tiene un número identificador, una fecha específica
 * y un estado actual (programada, realizada, cancelada, etc.).
 * 
 * Propiedades principales:
 * - Referencia al consejo al que pertenece
 * - Número y nombre de la sesión
 * - Fecha de realización
 * - Tipo de sesión (ordinaria, extraordinaria, etc.)
 * - Estado actual de la sesión
 * - Descripción y detalles de la sesión
 * - Fecha de creación del registro
 * - Registros de asistencia de los miembros
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Entity
@Table(name = "sesion_consejo")
@Data
public class SesionConsejoModel {

    /** Identificador único de la sesión */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private Integer idSesion;

    /** Referencia al consejo al que pertenece esta sesión */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consejo", nullable = false)
    private ConsejoModel consejo;

    /** Número de sesión (ej: "2024-001", "Sesión No. 45") */
    @Column(name = "numero_sesion", length = 50)
    private String numeroSesion;

    /** Nombre o título descriptivo de la sesión */
    @Column(name = "nombre_sesion", length = 255)
    private String nombreSesion;

    /** Fecha en que se realiza o se realizó la sesión */
    @Column(name = "fecha_sesion")
    private LocalDate fechaSesion;

    /** Identificador del tipo de sesión (referencia al catálogo: ORDINARIA, EXTRAORDINARIA, etc.) */
    @Column(name = "id_tipo_sesion")
    private Integer idTipoSesion;

    /** Descripción detallada de los temas a tratar en la sesión */
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    /** Identificador del estado actual de la sesión (referencia al catálogo de estados) */
    @Column(name = "id_estado")
    private Integer idEstado;

    /** Identificador del usuario que registró la sesión */
    @Column(name = "id_usuario_registro")
    private Integer usuarioRegistro;

    /** Fecha y hora de creación del registro de la sesión */
    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    /** Registros de asistencia de los miembros a esta sesión */
    @OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AsistenciaSesionModel> asistencias;

}

