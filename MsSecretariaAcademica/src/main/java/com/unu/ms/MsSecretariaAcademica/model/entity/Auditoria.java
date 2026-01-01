package com.unu.ms.MsSecretariaAcademica.model.entity;

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
 * Entidad que representa el registro de auditoría del sistema.
 *
 * Almacena la trazabilidad de las acciones realizadas sobre
 * las entidades del microservicio, permitiendo registrar
 * operaciones como creación, actualización, eliminación
 * y eliminación lógica (soft delete).
 *
 * La información registrada en esta entidad es propia del
 * microservicio y se utiliza con fines de control, seguimiento
 * y auditoría.
 */
@Entity
@Table(name = "auditoria")
@Data
public class Auditoria {

    /**
     * Identificador único del registro de auditoría.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAuditoria;

    /**
     * Nombre de la entidad auditada.
     */
    @Column(name = "nombre_entidad", nullable = false)
    private String nombreEntidad;

    /**
     * Identificador del registro de la entidad auditada.
     */
    @Column(name = "entidad_id", nullable = false)
    private Integer entidadId;

    /**
     * Acción realizada sobre la entidad.
     *
     * Valores comunes: CREATE, UPDATE, DELETE, SOFT_DELETE.
     */
    @Column(name = "accion", nullable = false)
    private String accion;

    /**
     * Identificador del usuario que realizó la acción.
     */
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    /**
     * Estado de los datos antes de la acción realizada.
     *
     * Se almacena generalmente en formato JSON.
     */
    @Lob
    @Column(name = "datos_antes")
    private String datosAntes;

    /**
     * Estado de los datos después de la acción realizada.
     *
     * Se almacena generalmente en formato JSON.
     */
    @Lob
    @Column(name = "datos_despues")
    private String datosDespues;

    /**
     * Fecha y hora en que se realizó la acción.
     *
     * Se genera automáticamente al momento de persistir el registro.
     */
    @CreationTimestamp
    @Column(name = "fecha_accion")
    private LocalDateTime fechaAccion;

    /**
     * Observación adicional relacionada con la acción realizada.
     */
    @Column(name = "observacion")
    private String observacion;

}