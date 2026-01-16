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

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
 * Entidad que representa un registro de asistencia en una sesión de consejo.
 * 
 * Cada registro de asistencia indica si un miembro específico del consejo
 * asistió o no a una sesión particular, y en qué estado (presente, ausente,
 * justificado, etc.). Esto es esencial para auditoría y cálculo de quórums.
 * 
 * Propiedades principales:
 * - Referencia a la sesión del consejo
 * - Referencia al miembro del consejo
 * - Estado de asistencia (presente, ausente, justificado, etc.)
 * - Usuario que registró la asistencia
 * - Fecha y hora del registro
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Entity
@Table(name = "asistencia_sesion")
@Data
public class AsistenciaSesionModel {

    /** Identificador único del registro de asistencia */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Integer idAsistencia;

    /** Referencia a la sesión en que se registra la asistencia */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sesion", nullable = false)
    private SesionConsejoModel sesion;

    /** Referencia al miembro del consejo cuya asistencia se registra */
    @ManyToOne
    @JoinColumn(name = "id_miembro", nullable = false)
    private MiembroConsejoModel miembro;

    /** 
     * Estado de asistencia del miembro (referencia al catálogo de estados de asistencia)
     * Ejemplo: PRESENTE, AUSENTE, JUSTIFICADO, EXCUSADO
     */
    @Column(name = "id_estado_asistencia", nullable = false)
    private Integer idEstadoAsistencia;

    /** Identificador del usuario que registró la asistencia */
    @Column(name = "id_usuario_registro")
    private Integer usuarioRegistro;

    /** Fecha y hora en que se registró la asistencia */
    @CreationTimestamp
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

}
