package com.unu.ms.MsGradosTitulos.model.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionResponse {
    
    public Integer idResolucion;
    public String numeroResolucion;
    // public ExpedienteModel expediente;
    public Integer idSolicitud;
    public Integer idEstado;
    public Integer idTipoResolucion;
    public LocalDate fechaEmision;
    public String resumen;
    public String fundamento;
    public String articuladoGeneral;
    public Boolean aprobadoEnSesion;
    public Integer usuarioCreo;
    // public Timestamp fechaCreacion;
    // public List<ResolucionArticuloModel> articulos;
    
}

