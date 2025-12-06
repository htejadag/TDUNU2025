package com.unu.ms.MsGradosTitulos.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para la entidad Expediente")
public class ExpedienteDTO {
    
    @Schema(description = "ID único del expediente", example = "1")
    private int idExpediente;
    
    @Schema(description = "Código único del expediente", example = "EXP-2025-001")
    private String codigoExpediente;
    
    @Schema(description = "ID de la persona asociada", example = "1")
    private int idPersona;
    
    @Schema(description = "ID del estado del expediente", example = "1")
    private int idEstado;
    
    @Schema(description = "Descripción del expediente", example = "Expediente de grados y títulos")
    private String descripcion;
    
    @Schema(description = "Fecha de apertura del expediente")
    private Date fechaApertura;
    
    @Schema(description = "Fecha de cierre del expediente")
    private Date fechaCierre;
    
    @Schema(description = "ID del usuario que creó el expediente", example = "1")
    private int idUsuarioCreo;
    
    @Schema(description = "Fecha de creación del expediente")
    private Date fechaCreacion;
    
    // Getters y Setters generados manualmente
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

