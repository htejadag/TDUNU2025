package com.service.MsTramiteTesis.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proyecto_tesis", indexes = {
        @Index(name = "idx_proyecto_estudiante", columnList = "id_estudiante"),
        @Index(name = "idx_proyecto_asesor", columnList = "id_asesor"),
        @Index(name = "idx_proyecto_estado", columnList = "estado_proyecto_cat")
})
public class ProyectoTesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Long idProyecto;

    @Column(name = "id_asesor", nullable = false)
    private Integer idAsesor;

    @Column(name = "id_estudiante", nullable = false)
    private Integer idEstudiante;

    @Column(name = "id_linea", nullable = false)
    private Integer idLinea;

    @Column(name = "titulo", nullable = false, columnDefinition = "TEXT")
    private String titulo;

    @Column(name = "ruta_archivo_proyecto", length = 255)
    private String rutaArchivoProyecto;

    @Column(name = "codigo_seguimiento", length = 50)
    private String codigoSeguimiento;

    @Column(name = "estado_proyecto_cat")
    private Integer estadoProyectoCat;

    @Column(name = "fecha_registro", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;

}
