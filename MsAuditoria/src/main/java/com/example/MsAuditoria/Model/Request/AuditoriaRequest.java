package com.example.MsAuditoria.Model.Request;

import lombok.Data;

@Data
public class AuditoriaRequest {

    private String microservicio;
    private String modulo;
    private String operacion;
    private String entidad;
    private String idEntidad;
    private Object datos;
    private String usuario;
}
