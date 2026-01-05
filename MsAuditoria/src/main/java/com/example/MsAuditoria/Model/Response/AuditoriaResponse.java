package com.example.MsAuditoria.Model.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AuditoriaResponse {

    private String idAuditoria;
    private String microservicio;
    private String modulo;
    private String operacion;
    private String entidad;
    private String idEntidad;
    private Object datos;
    private String usuario;
    private LocalDateTime fechaEvento;
}
