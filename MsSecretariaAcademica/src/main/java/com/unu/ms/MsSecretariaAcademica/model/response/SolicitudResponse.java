package com.unu.ms.MsSecretariaAcademica.model.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudResponse {
    
    Integer idSolicitud;
    String codigoSolicitud;
    Integer idPersona;
    Integer idTipoSolicitud;
    String asunto;
    String detalle;
    Integer idEstado;
    LocalDate fechaSolicitud;
    
}
