package com.unu.ms.MsGradosTitulos.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "resolucion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resolucion")
    private Integer idResolucion;

    @Column(name = "numero_resolucion", length = 100)
    private String numeroResolucion;

    @Column(name = "id_expediente")
    private Integer idExpediente;

    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "id_tipo_resolucion")
    private Integer idTipoResolucion;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "resumen", columnDefinition = "NVARCHAR(MAX)")
    private String resumen;

    @Column(name = "fundamento", columnDefinition = "NVARCHAR(MAX)")
    private String fundamento;

    @Column(name = "articulado_general", columnDefinition = "NVARCHAR(MAX)")
    private String articuladoGeneral;

    @Column(name = "aprobado_en_sesion")
    private Integer aprobadoEnSesion;

    @Column(name = "id_usuario_creo")
    private Integer idUsuarioCreo;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_expediente", insertable = false, updatable = false)
    private Expediente expediente;

    @OneToMany(mappedBy = "resolucion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResolucionArticulo> resolucionArticulos;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
}
