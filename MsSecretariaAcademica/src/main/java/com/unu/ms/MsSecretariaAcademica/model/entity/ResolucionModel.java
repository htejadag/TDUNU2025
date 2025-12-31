package com.unu.ms.MsSecretariaAcademica.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "resolucion")
@Data
public class ResolucionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resolucion")
    private Integer idResolucion;

    @Column(name = "numero_resolucion", nullable = false, unique = true)
    private String numeroResolucion;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "id_tipo_resolucion")
    private Integer idTipoResolucion;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "resumen")
    private String resumen;

    @Column(name = "fundamento")
    private String fundamento;

    @Column(name = "articulado_general")
    private String articuladoGeneral;

    @Column(name = "aprobado_en_sesion")
    private Integer aprobadoEnSesion;

    @OneToOne
    @JoinColumn(name = "id_expediente", nullable = false, unique = true)
    private ExpedienteModel expediente;

    @OneToMany(mappedBy = "resolucion", cascade = CascadeType.ALL)
    private List<ResolucionArticuloModel> articulos;
    
}

