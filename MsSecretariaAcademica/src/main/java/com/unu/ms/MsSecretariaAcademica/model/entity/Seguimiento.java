package com.unu.ms.MsSecretariaAcademica.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

/**
 * Entidad que representa el seguimiento de una entidad del sistema.
 *
 * Permite registrar el historial de estados, comentarios y
 * acciones asociadas a entidades como solicitudes, expedientes
 * o resoluciones, según el catálogo de entidades definido
 * dentro del microservicio.
 */
@Entity
@Data
@Table(
        name = "seguimiento",
        indexes = @Index(columnList = "entidadCatalogoId, entidadId")
)
public class Seguimiento {

    /**
     * Identificador único del registro de seguimiento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguimiento")
    private Integer idSeguimiento;

    /**
     * Identificador del usuario que realizó el registro del seguimiento.
     */
    @Column(name = "id_usuario")
    private Integer idUsuario;

    /**
     * Identificador del catálogo de la entidad seguida.
     *
     * Ejemplos: SOLICITUD, EXPEDIENTE, RESOLUCION.
     */
    @Column(name = "entidad_catalogo_id")
    private Integer entidadCatalogoId;

    /**
     * Identificador de la entidad a la que pertenece el seguimiento.
     */
    @Column(name = "entidad_id")
    private Integer entidadId;

    /**
     * Identificador del estado registrado en el seguimiento.
     */
    @Column(name = "id_estado")
    private Integer idEstado;

    /**
     * Comentario u observación asociada al seguimiento.
     */
    @Column(name = "comentario")
    private String comentario;

    /**
     * Fecha y hora de registro del seguimiento.
     *
     * Se genera automáticamente al momento de persistir el registro.
     */
    @CreationTimestamp
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

}
