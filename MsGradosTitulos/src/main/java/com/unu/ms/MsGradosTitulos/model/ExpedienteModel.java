package com.unu.ms.MsGradosTitulos.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "expediente")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_expediente")
    private int idExpediente;
    @Column(name = "codigo_expediente")
    private String codigoExpediente;
    @Column(name = "id_persona")
    private int idPersona;
    @Column(name = "id_estado")
    private int idEstado;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_apertura")
    private Date fechaApertura;
    @Column(name = "fecha_cierre")
    private Date fechaCierre;
    @Column(name = "id_usuario_creo")
    private int idUsuarioCreo;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @PrePersist
    protected void onCreate() {
        // Solo se establece si el campo no ha sido asignado previamente.
        if (this.fechaCreacion == null) {
            this.fechaCreacion = new Date();
        }
    }

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
