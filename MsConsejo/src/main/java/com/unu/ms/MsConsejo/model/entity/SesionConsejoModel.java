package com.unu.ms.MsConsejo.model.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sesion_consejo")
@Data
public class SesionConsejoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_consejo")
    private ConsejoModel consejo;

    @Column(name = "numero_sesion")
    private String numeroSesion;

    @Column(name = "fecha_sesion")
    private LocalDate fechaSesion;

    @Column(name = "id_tipo_sesion")
    private Integer idTipoSesion;

    private String descripcion;

    @Column(name = "id_estado")
    private Integer estado;

    @Column(name = "id_usuario_registro")
    private Integer usuarioRegistro;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @OneToMany(mappedBy = "sesion")
    private List<AsistenciaSesionModel> asistencias;
}

