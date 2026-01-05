package com.unu.ms.MsSecretariaAcademica.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO de respuesta para la entidad Expediente.
 *
 * Representa la información expuesta de un expediente dentro del sistema.
 * Un expediente agrupa y centraliza la información relacionada a una solicitud
 * y su posterior resolución, permitiendo su seguimiento a lo largo del tiempo.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteResponse {

    /**
     * Identificador único del expediente.
     */
    Integer idExpediente;

    /**
     * Código único del expediente.
     *
     * Utilizado como identificador funcional dentro del sistema.
     */
    String codigoExpediente;

    /**
     * Identificador de la persona asociada al expediente.
     */
    Integer idPersona;

    /**
     * Identificador del estado actual del expediente.
     *
     * Referencia a un catálogo de estados.
     */
    Integer idEstado;

    /**
     * Descripción general del expediente.
     */
    String descripcion;

    /**
     * Fecha de apertura del expediente.
     */
    LocalDate fechaApertura;

    /**
     * Fecha de cierre del expediente.
     *
     * Puede ser {@code null} mientras el expediente se encuentre activo.
     */
    LocalDate fechaCierre;

    /**
     * Fecha y hora de creación del expediente.
     *
     * Valor generado automáticamente por el sistema.
     */
    LocalDateTime fechaCreacion;

    /**
     * Identificador de la solicitud que dio origen al expediente.
     */
    Integer idSolicitudOrigen;

    /**
     * Otros atributos de la entidad
     *
     * ResolucionModel resolucion;
     */

}
