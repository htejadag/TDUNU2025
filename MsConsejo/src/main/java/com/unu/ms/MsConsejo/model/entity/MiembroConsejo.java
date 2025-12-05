package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "miembro_consejo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MiembroConsejo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_miembro")
    private Integer idMiembro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consejo", nullable = false)
    private Consejo consejo;

    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Column(name = "id_cargo", nullable = false)
    private Integer idCargo;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;
}
