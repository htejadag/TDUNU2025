package com.unu.ms.MsSecretariaAcademica.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de respuesta para el seguimiento de entidades.
 *
 * Representa la información de seguimiento registrada sobre una entidad
 * del sistema, utilizada para mostrar el historial de cambios,
 * observaciones o estados asociados a una entidad específica.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoResponse {

    /**
     * Identificador único del seguimiento.
     */
    Integer idSeguimiento;

    /**
     * Identificador del usuario que realizó el registro del seguimiento.
     */
    Integer idUsuario;

    /**
     * Identificador del catálogo que define el tipo de entidad
     * sobre la cual se realiza el seguimiento.
     */
    Integer entidadCatalogoId;

    /**
     * Identificador de la entidad asociada al seguimiento.
     *
     * Corresponde al identificador de la entidad específica
     * (por ejemplo: solicitud, resolución, expediente, etc.).
     */
    Integer entidadId;

    /**
     * Identificador del estado asociado al seguimiento.
     */
    Integer idEstado;

    /**
     * Comentario u observación registrada en el seguimiento.
     */
    String comentario;

    /**
     * Fecha y hora en la que se registró el seguimiento.
     */
    LocalDateTime fechaRegistro;

}