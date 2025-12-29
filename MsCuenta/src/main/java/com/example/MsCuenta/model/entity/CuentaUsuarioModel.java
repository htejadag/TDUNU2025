package com.example.MsCuenta.model.entity;

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
@Table(name = "cuenta_usuario")

public class CuentaUsuarioModel {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_usuario_rol", nullable = false)
    private Integer idUsuarioRol;

    @Column(name = "saldo", nullable = false)
    private double saldo;
    @Column (name = "activo", nullable = false)
    private boolean activo;
    
    //auditoria
    @Column(name = "usuario_creacion", nullable = false)
    private Integer usuarioCreacion;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;
    @Column(name = "usuario_modificacion")
    private Integer usuarioModificacion;
    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

   
    
}