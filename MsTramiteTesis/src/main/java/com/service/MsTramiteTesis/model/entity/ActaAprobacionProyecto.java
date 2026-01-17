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
@Table(name = "acta_aprobacion_proyecto")
public class ActaAprobacionProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acta_proy")
    private Long idActaProy;

    @Column(name = "id_proyecto", nullable = false, unique = true)
    private Long idProyecto;

    @Column(name = "codigo_acta", length = 50)
    private String codigoActa;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "ruta_pdf", length = 255)
    private String rutaPdf;

}
