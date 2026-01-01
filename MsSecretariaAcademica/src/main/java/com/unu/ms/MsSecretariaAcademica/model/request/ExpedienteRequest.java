package com.unu.ms.MsSecretariaAcademica.model.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para la creación y actualización de expedientes.
 *
 * Representa los datos de entrada necesarios para registrar
 * o modificar un expediente dentro del microservicio.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteRequest {

    /**
     * Código único del expediente.
     */
    String codigoExpediente;

    /**
     * Identificador de la persona asociada al expediente.
     */
    Integer idPersona;

    /**
     * Identificador del estado actual del expediente.
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
     */
    LocalDate fechaCierre;

    /**
     * Identificador de la solicitud que dio origen al expediente.
     */
    Integer idSolicitudOrigen;

    /**
     * Otros atributos de la entidad
     * 
     * Integer idExpediente;
     * LocalDateTime fechaCreacion;
     * ResolucionModel resolucion;
     */ 

}
