package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
 * Entidad que registra un registro de auditoría del sistema.
 * 
 * La auditoría es fundamental para cumplimiento normativo y seguimiento de cambios.
 * Cada operación (CREATE, UPDATE, DELETE) sobre las entidades principales se registra
 * con detalles completos para trazabilidad.
 * 
 * Propiedades principales:
 * - Nombre de la entidad afectada
 * - ID de la instancia de la entidad
 * - Tipo de acción realizada (CREATE, UPDATE, DELETE, SOFT_DELETE)
 * - Usuario que realizó la acción
 * - Estado de los datos antes y después de la operación
 * - Fecha y hora de la acción
 * - Observaciones adicionales
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Entity
@Table(name = "auditoria")
@Data
public class Auditoria {

    /** Identificador único del registro de auditoría */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAuditoria;

    /** Nombre de la entidad que fue modificada (ej: "Consejo", "Sesion", "Miembro") */
    @Column(name = "nombre_entidad", nullable = false)
    private String nombreEntidad;

    /** Identificador de la instancia de la entidad que fue modificada */
    @Column(name = "entidad_id", nullable = false)
    private Integer entidadId;

    /** 
     * Tipo de acción realizada sobre la entidad.
     * Valores posibles: CREATE, UPDATE, DELETE, SOFT_DELETE
     */
    @Column(name = "accion", nullable = false)
    private String accion;

    /** Identificador del usuario que realizó la acción */
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    /** Representación JSON de los datos de la entidad antes de la acción (para UPDATE y DELETE) */
    @Lob
    @Column(name = "datos_antes", columnDefinition = "TEXT")
    private String datosAntes;

    /** Representación JSON de los datos de la entidad después de la acción (para CREATE y UPDATE) */
    @Lob
    @Column(name = "datos_despues", columnDefinition = "TEXT")
    private String datosDespues;

    /** Fecha y hora en que se realizó la acción */
    @CreationTimestamp
    @Column(name = "fecha_accion")
    private LocalDateTime fechaAccion;

    /** Observaciones o notas adicionales sobre la acción realizada */
    @Column(name = "observacion")
    private String observacion;

}
