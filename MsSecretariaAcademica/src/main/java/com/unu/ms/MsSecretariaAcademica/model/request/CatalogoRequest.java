package com.unu.ms.MsSecretariaAcademica.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para la creación y actualización de catálogos.
 *
 * Contiene los datos necesarios para registrar o modificar
 * valores de catálogo dentro del microservicio.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoRequest {

    /**
     * Identificador del catálogo.
     */
    Integer idCatalogo;

    /**
     * Categoría del catálogo.
     */
    String categoria;

    /**
     * Valor del catálogo.
     */
    String valor;

    /**
     * Descripción del valor del catálogo.
     */
    String descripcion;

}
