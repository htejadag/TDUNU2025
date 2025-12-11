package com.unu.ms.MsSecretariaAcademica.model.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionRequest {
    
    // public Integer idResolucion;
    // public String numeroResolucion;
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
    // public LocalDateTime fechaCreacion;
    // public List<ResolucionArticuloModel> articulos;

}
