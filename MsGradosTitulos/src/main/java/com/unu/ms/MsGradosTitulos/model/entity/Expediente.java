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
@Table(name = "expediente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expediente")
    private Integer idExpediente;

    @Column(name = "codigo_expediente", length = 50)
    private String codigoExpediente;

    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "descripcion", columnDefinition = "NVARCHAR(MAX)")
    private String descripcion;

    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;

    @Column(name = "fecha_cierre")
    private LocalDate fechaCierre;

    @Column(name = "id_usuario_creo")
    private Integer idUsuarioCreo;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "expediente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resolucion> resoluciones;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
}
