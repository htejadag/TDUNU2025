package com.unu.ms.MsSecretariaAcademica.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para la creación y actualización de artículos
 * asociados a una resolución.
 *
 * Contiene la información del articulado que forma parte
 * de una resolución dentro del microservicio.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionArticuloRequest {

    /**
     * Número del artículo dentro de la resolución.
     */
    Integer numeroArticulo;

    /**
     * Título del artículo.
     */
    String titulo;

    /**
     * Contenido o texto completo del artículo.
     */
    String contenido;

    /**
     * Identificador de la resolución a la que pertenece el artículo.
     */
    Integer resolucion;

    /**
     * Otros atributos de la entidad
     * 
     * Integer idArticulo;
     */

}