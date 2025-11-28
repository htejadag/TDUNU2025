package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Entity
@Table(name = "consejo")
@Data
public class ConsejoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consejo")
    private Integer idConsejo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @OneToMany(mappedBy = "consejo")
    private List<MiembroConsejoModel> miembros;

    @OneToMany(mappedBy = "consejo")
    private List<SesionConsejoModel> sesiones;

}