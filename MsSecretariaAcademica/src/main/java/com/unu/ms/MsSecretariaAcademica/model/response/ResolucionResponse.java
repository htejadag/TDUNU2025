package com.unu.ms.MsSecretariaAcademica.model.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de respuesta para resoluciones.
 *
 * Representa la información expuesta de una resolución hacia el exterior
 * del sistema (API, frontend u otros servicios).
 * Contiene únicamente los datos relevantes y evita exponer
 * directamente las entidades JPA.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionResponse {

    /**
     * Identificador único de la resolución.
     */
    Integer idResolucion;

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
     *
     * Contiene el texto completo del articulado principal
     * cuando no se maneja por artículos individuales.
     */
    String articuladoGeneral;

    /**
     * Indicador de aprobación de la resolución en sesión.
     *
     * Generalmente representa el identificador de la sesión
     * enviada por el microservicio MsConsejo.
     */
    Integer aprobadoEnSesion;

    /**
     * Identificador del expediente asociado a la resolución.
     */
    Integer expediente;

    /**
     * Lista de artículos asociados a la resolución.
     *
     * Se omite en este DTO para evitar sobrecarga de datos
     * o dependencias circulares.
     * 
     * List<ResolucionArticuloModel> articulos;
     */

}

