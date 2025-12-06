package com.unu.ms.MsGradosTitulos.model;

import java.util.Date;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "resolucion")
public class ResolucionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResolucion;

    private String numeroResolucion;
    
    private int idExpediente;
    
    private int idSolicitud;
    
    private int idEstado;
    
    private int idTipoResolucion;
    
    private Date fechaEmision;
    
    private String resumen;
    
    private String fundamento;
    
    private String articuladoGeneral;
    
    private int aprobadoEnSesion;
    
    private int idUsuarioCreo;
    
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "idExpediente", insertable = false, updatable = false)
    private ExpedienteModel expediente;
}
