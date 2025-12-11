package com.unu.ms.MsSecretariaAcademica.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteResponse {
    
    public Integer idExpediente;
    public String codigoExpediente;
    public Integer idPersona;
    public Integer idEstado;
    public String descripcion;
    public LocalDate fechaApertura;
    public LocalDate fechaCierre;
    public Integer usuarioCreo;
    public LocalDateTime fechaCreacion;
    // public List<ResolucionModel> resoluciones;
    
}
