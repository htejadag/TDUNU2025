package com.unu.ms.MsGradosTitulos.model.request;

import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteRequest {
    
    // public Integer idExpediente;
    public String codigoExpediente;
    public Integer idPersona;
    public Integer idEstado;
    public String descripcion;
    public LocalDate fechaApertura;
    public LocalDate fechaCierre;
    public Integer usuarioCreo;
    public Timestamp fechaCreacion;
    // public List<ResolucionModel> resoluciones;
    
}
