package com.service.MsTramiteTesis.model.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proyecto_tesis", indexes = {
        @Index(name = "idx_proyecto_estudiante_ext", columnList = "id_estudiante_ext"),
        @Index(name = "idx_proyecto_asesor_ext", columnList = "id_asesor_ext"),
        @Index(name = "idx_proyecto_estado_codigo", columnList = "estado_proyecto_codigo")
})
public class ProyectoTesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Long idProyecto;

    @Column(name = "id_estudiante_ext", nullable = false)
    private Long idEstudianteExt;

    @Column(name = "id_asesor_ext", nullable = false)
    private Long idAsesorExt;

    @Column(name = "id_especialidad_ext", nullable = false)
    private Long idEspecialidadExt;

    @Column(name = "titulo_proyecto", nullable = false, columnDefinition = "TEXT")
    private String tituloProyecto;

    @Column(name = "ruta_pdf_proyecto", columnDefinition = "TEXT")
    private String rutaPdfProyecto;

    @Column(name = "estado_proyecto_codigo", nullable = false, length = 60)
    private String estadoProyectoCodigo;

    @Column(name = "fecha_registro", nullable = false, columnDefinition = "TIMESTAMPTZ DEFAULT now()")
    private OffsetDateTime fechaRegistro;

    @Column(name = "fecha_aprobacion_final", columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime fechaAprobacionFinal;

}
