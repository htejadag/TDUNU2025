package com.example.MsPlanEstudios.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "plan_estudio")
public class PlanEstudiosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "idCarrera")
    private Integer idCarrera;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "año")
    private String año;

    @Column(name = "estado")
    private boolean estado;

    @Column(name = "usuarioCreacion")
    private Integer usuarioCreacion;

    @Column(name = "usuarioModificacion")
    private Integer usuarioModificacion;

    @Column(name = "fechaCreacion")
    private String fechaCreacion;

    @Column(name = "fechaModificacion")
    private String fechaModificacion;
}
