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
@Table(name = "borrador_tesis", indexes = {
        @Index(name = "idx_borrador_proyecto", columnList = "id_proyecto"),
        @Index(name = "idx_borrador_estado_codigo", columnList = "estado_borrador_codigo")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uq_borrador_proyecto_version", columnNames = { "id_proyecto", "version_num" })
})
public class BorradorTesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_borrador")
    private Long idBorrador;

    @Column(name = "id_proyecto", nullable = false)
    private Long idProyecto;

    @Column(name = "version_num", nullable = false)
    private Integer versionNum = 1;

    @Column(name = "resumen_abstract", columnDefinition = "TEXT")
    private String resumenAbstract;

    @Column(name = "palabras_clave", columnDefinition = "TEXT")
    private String palabrasClave;

    @Column(name = "conclusiones", columnDefinition = "TEXT")
    private String conclusiones;

    @Column(name = "cumple_plazo_ejecucion", nullable = false)
    private Boolean cumplePlazoEjecucion = false;

    @Column(name = "estado_borrador_codigo", nullable = false, length = 60)
    private String estadoBorradorCodigo;

    @Column(name = "ruta_pdf_borrador", columnDefinition = "TEXT")
    private String rutaPdfBorrador;

    @Column(name = "fecha_registro_borrador", nullable = false, columnDefinition = "TIMESTAMPTZ DEFAULT now()")
    private OffsetDateTime fechaRegistroBorrador;

    @PrePersist
    protected void onCreate() {
        if (fechaRegistroBorrador == null) {
            fechaRegistroBorrador = OffsetDateTime.now();
        }
        if (versionNum == null) {
            versionNum = 1;
        }
        if (cumplePlazoEjecucion == null) {
            cumplePlazoEjecucion = false;
        }
    }
}
