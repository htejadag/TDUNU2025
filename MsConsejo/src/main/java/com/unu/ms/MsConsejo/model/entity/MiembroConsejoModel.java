package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "miembro_consejo")
@Data
@EqualsAndHashCode(callSuper = true)
public class MiembroConsejoModel extends AuditoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_miembro")
    private Integer idMiembro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consejo", nullable = false)
    private ConsejoModel consejo;

    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Column(name = "id_cargo", nullable = false)
    private Integer idCargo;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @OneToMany(mappedBy = "miembro")
    private List<AsistenciaSesionModel> asistencias;

}
