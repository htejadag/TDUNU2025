package com.service.MsTramiteTesis.model.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "asignacion_jurado")
public class AsignacionJurado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Long idAsignacion;

    @Column(name = "id_proyecto", nullable = false)
    private Long idProyecto;

    @Column(name = "id_docente", nullable = false)
    private Integer idDocente;

    @Column(name = "rol_jurado_cat")
    private Integer rolJuradoCat;

    @Column(name = "fecha_sorteo")
    private LocalDate fechaSorteo;

    @Column(name = "activo", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean activo;

}
