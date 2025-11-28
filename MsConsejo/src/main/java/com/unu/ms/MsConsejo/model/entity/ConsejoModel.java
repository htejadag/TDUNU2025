package com.unu.ms.MsConsejo.model.entity;

import java.sql.Timestamp;
import java.util.List;
import jakarta.persistence.*;
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