package com.unu.ms.MsSecretariaAcademica.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "catalogo", uniqueConstraints = @UniqueConstraint(columnNames = { "categoria", "valor" }))
@Data
public class Catalogo implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCatalogo;

    @Column(nullable = false)
    private String categoria; // ESTADO_SOLICITUD, TIPO_RESOLUCION, ENTIDAD_SEGUIMIENTO

    @Column(nullable = false)
    private String valor; // REGISTRADO, APROBADO, OBSERVADO

    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
}