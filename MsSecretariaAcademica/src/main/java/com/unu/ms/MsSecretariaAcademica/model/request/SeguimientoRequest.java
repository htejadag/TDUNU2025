package com.unu.ms.MsSecretariaAcademica.model.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para la creación y registro de seguimientos.
 *
 * Permite almacenar el historial de cambios de estado y observaciones
 * asociadas a entidades del microservicio (solicitudes, expedientes,
 * resoluciones, etc.).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoRequest {

    /**
     * Identificador del usuario que realiza la acción.
     */
    Integer idUsuario;

    /**
     * Identificador del catálogo que representa el tipo de entidad
     * sobre la cual se realiza el seguimiento.
     *
     * Ejemplos: SOLICITUD, EXPEDIENTE, RESOLUCION.
     */
    Integer entidadCatalogoId;

    /**
     * Identificador de la entidad específica a la que se asocia el seguimiento.
     */
    Integer entidadId;

    /**
     * Identificador del estado asociado al seguimiento.
     */
    Integer idEstado;

    /**
     * Comentario u observación registrada durante el seguimiento.
     */
    String comentario;

    /**
     * Otros atributos de la entidad
     * 
     * Integer idSeguimiento;
     * LocalDateTime fechaRegistro;
     */

}