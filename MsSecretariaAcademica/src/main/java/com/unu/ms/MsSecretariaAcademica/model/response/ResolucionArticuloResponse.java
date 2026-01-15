package com.unu.ms.MsSecretariaAcademica.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de respuesta para los artículos de una resolución.
 *
 * Representa la información expuesta de un artículo asociado a una resolución.
 * Cada artículo forma parte del articulado de una resolución y define
 * disposiciones específicas dentro de la misma.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionArticuloResponse {

    /**
     * Identificador único del artículo de resolución.
     */
    Integer idArticulo;

    /**
     * Número ordinal del artículo dentro de la resolución.
     *
     * Usualmente representa el orden secuencial del artículo
     * (Artículo 1, Artículo 2, etc.).
     */
    Integer numeroArticulo;

    /**
     * Título del artículo.
     */
    String titulo;

    /**
     * Contenido completo del artículo.
     *
     * Describe la disposición normativa o administrativa
     * definida por el artículo.
     */
    String contenido;

    /**
     * Identificador de la resolución a la que pertenece el artículo.
     */
    Integer resolucion;

}