package com.unu.ms.MsSecretariaAcademica.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

/**
 * Entidad que representa una resolución dentro del sistema.
 *
 * Una resolución es el resultado formal de un expediente y
 * contiene información estructurada como datos generales,
 * fundamentos, articulado y su relación con artículos y
 * el expediente asociado.
 */
@Entity
@Table(name = "resolucion")
@Data
public class ResolucionModel {

    /**
     * Identificador único de la resolución.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resolucion")
    private Integer idResolucion;

    /**
     * Número único de la resolución.
     */
    @Column(name = "numero_resolucion", nullable = false, unique = true)
    private String numeroResolucion;

    /**
     * Identificador del estado actual de la resolución.
     */
    @Column(name = "id_estado")
    private Integer idEstado;

    /**
     * Identificador del tipo de resolución.
     */
    @Column(name = "id_tipo_resolucion")
    private Integer idTipoResolucion;

    /**
     * Fecha de emisión de la resolución.
     */
    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    /**
     * Resumen general de la resolución.
     */
    @Column(name = "resumen")
    private String resumen;

    /**
     * Fundamento legal o técnico de la resolución.
     */
    @Column(name = "fundamento")
    private String fundamento;

    /**
     * Articulado general de la resolución.
     */
    @Column(name = "articulado_general")
    private String articuladoGeneral;

    /**
     * Indicador de aprobación de la resolución en sesión.
     *
     * Generalmente utilizado como bandera que indica el codigo de la sesion enviada por el microservicio MsConsejo.
     */
    @Column(name = "aprobado_en_sesion")
    private Integer aprobadoEnSesion;

    /**
     * Expediente asociado a la resolución.
     *
     * Relación uno a uno con la entidad de expediente.
     */
    @OneToOne
    @JoinColumn(name = "id_expediente", nullable = false, unique = true)
    private ExpedienteModel expediente;

    /**
     * Lista de artículos asociados a la resolución.
     *
     * Relación uno a muchos con la entidad de artículos de resolución.
     */
    @OneToMany(mappedBy = "resolucion", cascade = CascadeType.ALL)
    private List<ResolucionArticuloModel> articulos;

}