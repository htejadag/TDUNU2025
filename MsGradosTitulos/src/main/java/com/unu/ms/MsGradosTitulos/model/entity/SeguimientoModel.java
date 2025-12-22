package com.unu.ms.MsGradosTitulos.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

import lombok.Data;

@Entity
@Table(name = "seguimiento")
@Data
public class SeguimientoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguimiento")
    private Integer idSeguimiento;

    @Column(name = "id_entidad_catalogo")
    private Integer idEntidadCatalogo;

    @Column(name = "entidad_id")
    private Integer entidadId;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;
    
}
