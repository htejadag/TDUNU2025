package com.unu.ms.MsSecretariaAcademica.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Data;

/**
 * Entidad que representa un catálogo del sistema.
 *
 * Se utiliza para almacenar valores parametrizables del
 * microservicio, organizados por categoría y valor.
 * Estos catálogos forman parte del dominio propio del
 * microservicio y son utilizados para configuración,
 * clasificación y validación de información.
 */
@Entity
@Table(
        name = "catalogo",
        uniqueConstraints = @UniqueConstraint(columnNames = { "categoria", "valor" })
)
@Data
public class Catalogo implements Serializable {

    /**
     * Identificador único del catálogo.
     */
    @Id
    private Integer idCatalogo;

    /**
     * Categoría a la que pertenece el valor del catálogo.
     */
    @Column(nullable = false)
    private String categoria;

    /**
     * Valor del catálogo dentro de la categoría.
     */
    @Column(nullable = false)
    private String valor;

    /**
     * Descripción adicional del valor del catálogo.
     */
    @Column(columnDefinition = "TEXT")
    private String descripcion;

}