package com.example.Comedor.model.request;


import java.sql.Date;

import lombok.Data;

@Data
public class TurnoRequest {

    private String descripcion;
    private String horaApertura;
    private String horaCierre;
    private boolean activo;

    private Integer usuarioCreacion;

    private Date fechaCreacion;

    private Integer usuarioModificacion;
    
    private Date fechaModificacion;

    
}
