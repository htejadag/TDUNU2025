package com.example.MsCuenta.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "catalogo")
public class CatalogoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "tipo")
    private String tipo;
    @Column (name = "activo")
    private boolean activo;

    //auditoria
    @Column(name = "usuario_creacion", nullable = false, length = 100)
    private Integer usuarioCreacion;
    @Column(name = "fecha_creacion", nullable = false)
    private String fechaCreacion;
    @Column(name = "usuario_modificacion", length = 100)
    private Integer usuarioModificacion;
    @Column(name = "fecha_modificacion")
    private String fechaModificacion;
    
}
