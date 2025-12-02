package com.unu.ms.MsGradosTitulos.model;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "resolucion_articulo")
public class ResolucionArticuloModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArticulo;

    private int idResolucion;

    private int numeroArticulo;

    private String titulo;

    private String contenido;

    @ManyToOne
    @JoinColumn(name = "idResolucion", insertable = false, updatable = false)
    private ResolucionModel resolucion;
}
