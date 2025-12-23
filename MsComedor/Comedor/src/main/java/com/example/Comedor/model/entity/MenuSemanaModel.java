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
@Table(name = "menu_semana")
public class MenuSemanaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name = "fecha_inicio",nullable = false)
    private LocalDate fechaInicio;
    @Column(name = "fecha_fin",nullable = false)
    private LocalDate fechaFin;
    @Column(name = "activo",nullable = false)
    private boolean activo;

    //auditoria
    @Column(name = "usuario_creacion",nullable = false)
    private Integer usuarioCreacion;
    @Column(name = "fecha_creacion",nullable = false)
    private LocalDate fechaCreacion;
    @Column(name = "usuario_modificacion")
    private Integer usuarioModificacion;
    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;
 
}
