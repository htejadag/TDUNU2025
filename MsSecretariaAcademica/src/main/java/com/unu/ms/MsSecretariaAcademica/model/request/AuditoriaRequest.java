package com.unu.ms.MsSecretariaAcademica.model.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaRequest {

    Integer idAuditoria;
    String nombreEntidad;
    Integer entidadId;
    String accion;
    Integer usuarioId;
    String datosAntes;
    String datosDespues;
    LocalDateTime fechaAccion;
    String observacion;

}