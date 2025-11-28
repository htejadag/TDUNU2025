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
    private Integer id;

    private String nombre;

    private String descripcion;

    @Column(name = "id_estado")
    private Integer estado;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @OneToMany(mappedBy = "consejo")
    private List<MiembroConsejoModel> miembros;

    @OneToMany(mappedBy = "consejo")
    private List<SesionConsejoModel> sesiones;
}