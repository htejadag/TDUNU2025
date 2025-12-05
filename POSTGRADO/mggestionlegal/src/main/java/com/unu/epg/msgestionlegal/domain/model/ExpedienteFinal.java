package com.unu.epg.msgestionlegal.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "expediente_final")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpedienteFinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "fecha_recepcion")
    private LocalDate fechaRecepcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_expediente")
    private EstadoExpediente estadoExpediente;

    @Column(length = 500)
    private String observaciones;

    @Column(name = "fecha_finalizacion")
    private LocalDate fechaFinalizacion;

    // Setter personalizado (usado en el service)
    public void setEstado(EstadoExpediente estado) {
        this.estadoExpediente = estado;
    }
}
