package com.example.Comedor.model.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "plato")
public class PlatoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "imagen_url")
    private String imagenUrl;
    @Column(name = "calorias")
    private Integer calorias;
    @Column(name = "tipo")
    private String tipo;
     @Column(name = "activo")
    private boolean activo;


    //auditoria
    @Column(name = "usuario_creacion", nullable = false, length = 100)
    private String usuarioCreacion;
    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;
    @Column(name = "usuario_modificacion", length = 100)
    private String usuarioModificacion;
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;
    
}
