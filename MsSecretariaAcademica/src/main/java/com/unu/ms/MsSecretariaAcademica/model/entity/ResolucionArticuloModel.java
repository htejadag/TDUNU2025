package com.unu.ms.MsSecretariaAcademica.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * Entidad que representa un artículo asociado a una resolución.
 *
 * Cada artículo forma parte del contenido estructurado de una
 * resolución y permite organizarla en secciones numeradas,
 * con título y contenido descriptivo.
 */
@Entity
@Table(name = "resolucion_articulo")
@Data
public class ResolucionArticuloModel {

    /**
     * Identificador único del artículo de la resolución.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo")
    private Integer idArticulo;

    /**
     * Número de artículo dentro de la resolución.
     */
    @Column(name = "numero_articulo")
    private Integer numeroArticulo;

    /**
     * Título del artículo de la resolución.
     */
    @Column(name = "titulo")
    private String titulo;

    /**
     * Contenido del artículo de la resolución.
     */
    @Column(name = "contenido")
    private String contenido;

    /**
     * Resolución a la que pertenece el artículo.
     *
     * Relación muchos a uno con la entidad de resolución.
     */
    @ManyToOne
    @JoinColumn(name = "id_resolucion", nullable = false)
    private ResolucionModel resolucion;

}
