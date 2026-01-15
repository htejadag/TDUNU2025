package com.unu.ms.MsSecretariaAcademica.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de respuesta para la entidad Catálogo.
 *
 * Representa la información expuesta hacia el exterior del microservicio
 * sobre un registro de catálogo. Los catálogos son utilizados como
 * valores parametrizables y de referencia (estados, tipos, categorías, etc.)
 * dentro del dominio del sistema.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoResponse {

    /**
     * Identificador único del catálogo.
     */
    Integer idCatalogo;

    /**
     * Categoría a la que pertenece el catálogo.
     *
     * Ejemplos: ESTADO_EXPEDIENTE, TIPO_SOLICITUD, TIPO_RESOLUCION.
     */
    String categoria;

    /**
     * Valor del catálogo dentro de la categoría.
     *
     * Generalmente representa el código funcional del catálogo.
     */
    String valor;

    /**
     * Descripción textual del catálogo.
     *
     * Utilizada para mostrar información legible al usuario.
     */
    String descripcion;

}