package com.example.Comedor.model.entity;

import java.time.LocalDate;

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
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name = "nombre",nullable = false)
    private String nombre;
    @Column(name = "descripcion",nullable = false)
    private String descripcion;
    @Column(name = "imagen_url",nullable = false)
    private String imagenUrl;
    @Column(name = "calorias",nullable = false)
    private Integer calorias;
    @Column(name = "tipo",nullable = false)
    private String tipo;
    @Column(name = "activo",nullable = false)
    private boolean activo;


    //auditoria
    @Column(name = "usuario_creacion",nullable = false)
    private Integer usuarioCreacion;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;
    @Column(name = "usuario_modificacion")
    private Integer usuarioModificacion;
    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;
    
}
