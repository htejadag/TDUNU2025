package com.unu.ms.MsGradosTitulos.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expediente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idExpediente;

    private String codigoExpediente;
    
    private int idPersona;
    
    private int idEstado;
    
    private String descripcion;
    
    private Date fechaApertura;
    
    private Date fechaCierre;
    
    private int idUsuarioCreo;
    
    private Date fechaCreacion;
    
    // Getters y Setters
    public int getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(int idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getCodigoExpediente() {
        return codigoExpediente;
    }

    public void setCodigoExpediente(String codigoExpediente) {
        this.codigoExpediente = codigoExpediente;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public int getIdUsuarioCreo() {
        return idUsuarioCreo;
    }

    public void setIdUsuarioCreo(int idUsuarioCreo) {
        this.idUsuarioCreo = idUsuarioCreo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
