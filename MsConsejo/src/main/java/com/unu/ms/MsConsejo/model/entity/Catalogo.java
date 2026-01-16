package com.unu.ms.MsConsejo.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Data;

/**
 * Entidad que representa un catálogo en el sistema.
 * 
 * Los catálogos son valores dinámicos organizados por categorías que se utilizan
 * en toda la aplicación para proveer datos de referencia reutilizables.
 * 
 * Cada catálogo tiene una combinación única de categoría y valor, lo que permite
 * agrupar catálogos relacionados por categoría y buscarlos rápidamente.
 * 
 * Ejemplos de categorías: ESTADO_CONSEJO, ESTADO_SESION, TIPO_MIEMBRO, etc.
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Entity
@Table(name = "catalogo", uniqueConstraints = @UniqueConstraint(columnNames = { "categoria", "valor" }))
@Data
public class Catalogo implements Serializable {

    /** Identificador único del catálogo */
    @Id
    private Integer idCatalogo;

    /** Categoría a la que pertenece el catálogo (ej: ESTADO_CONSEJO, TIPO_MIEMBRO) */
    @Column(nullable = false)
    private String categoria;

    /** Valor o código del catálogo (ej: ACTIVO, INACTIVO, DIRECTOR) */
    @Column(nullable = false)
    private String valor;

    /** Descripción detallada del catálogo */
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
}