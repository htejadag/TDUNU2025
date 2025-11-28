package com.unu.ms.MsConsejo.model.entity;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "miembro_consejo")
@Data
public class MiembroConsejoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_miembro")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_consejo")
    private ConsejoModel consejo;

    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "id_cargo")
    private Integer idCargo;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @OneToMany(mappedBy = "miembro")
    private List<AsistenciaSesionModel> asistencias;
}
