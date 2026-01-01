package com.unu.ms.MsSecretariaAcademica.model.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para la creación y actualización de resoluciones.
 *
 * Contiene la información principal de una resolución,
 * incluyendo su estado, tipo, contenido y relación con un expediente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionRequest {

    /**
     * Número único de la resolución.
     */
    String numeroResolucion;

    /**
     * Identificador del estado actual de la resolución.
     */
    Integer idEstado;

    /**
     * Identificador del tipo de resolución.
     */
    Integer idTipoResolucion;

    /**
     * Fecha de emisión de la resolución.
     */
    LocalDate fechaEmision;

    /**
     * Resumen general de la resolución.
     */
    String resumen;

    /**
     * Fundamento legal o normativo de la resolución.
     */
    String fundamento;

    /**
     * Articulado general de la resolución.
     */
    String articuladoGeneral;

    /**
     * Indicador de aprobación en sesión.
     *
     * Generalmente representa con el id de la sesion enviada por el MsConsejo.
     */
    Integer aprobadoEnSesion;

    /**
     * Identificador del expediente asociado a la resolución.
     */
    Integer expediente;

    /**
     * Otros atributos de la entidad
     * 
     * Integer idResolucion;
     * List<ResolucionArticuloModel> articulos;
     */

}
