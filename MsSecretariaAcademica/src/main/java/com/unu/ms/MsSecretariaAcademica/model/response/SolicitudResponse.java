package com.unu.ms.MsSecretariaAcademica.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudResponse {
    
    public Integer idSolicitud;
    public String codigoSolicitud;
    public Integer idPersona;
    public Integer idTipoSolicitud;
    public Integer idExpediente;
    public String asunto;
    public String detalle;
    public Integer idEstado;
    public LocalDate fechaSolicitud;
    public Integer idUsuarioCreo;
    public LocalDateTime fechaCreacion;
    
}
