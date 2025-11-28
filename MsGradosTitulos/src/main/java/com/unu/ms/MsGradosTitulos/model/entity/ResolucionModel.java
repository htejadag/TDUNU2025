package com.unu.ms.MsGradosTitulos.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "resolucion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resolucion")
    private Integer id;

    @Column(name = "numero_resolucion")
    private String numeroResolucion;

    @ManyToOne
    @JoinColumn(name = "id_expediente")
    private ExpedienteModel expediente;

    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "id_tipo_resolucion")
    private Integer idTipoResolucion;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    private String resumen;

    private String fundamento;

    @Column(name = "articulado_general")
    private String articuladoGeneral;

    @Column(name = "aprobado_en_sesion")
    private Boolean aprobadoEnSesion;

    @Column(name = "id_usuario_creo")
    private Integer usuarioCreo;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @OneToMany(mappedBy = "resolucion")
    private List<ResolucionArticuloModel> articulos;
}

