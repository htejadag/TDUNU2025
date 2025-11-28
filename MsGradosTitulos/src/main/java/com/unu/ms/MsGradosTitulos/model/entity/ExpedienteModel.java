package com.unu.ms.MsGradosTitulos.model.entity;

import lombok.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "expediente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expediente")
    private Integer id;

    @Column(name = "codigo_expediente")
    private String codigoExpediente;

    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "id_estado")
    private Integer idEstado;

    private String descripcion;

    @Column(name = "fecha_aperura")
    private LocalDate fechaApertura;

    @Column(name = "fecha_cierre")
    private LocalDate fechaCierre;

    @Column(name = "id_usuario_creo")
    private Integer usuarioCreo;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @OneToMany(mappedBy = "expediente")
    private List<ResolucionModel> resoluciones;
}
