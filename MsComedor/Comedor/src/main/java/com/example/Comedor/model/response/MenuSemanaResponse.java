package com.example.Comedor.model.response;

import java.sql.Date;

import lombok.Data;

@Data
public class MenuSemanaResponse {

    private Integer id;
    
    private Date fechaInicio;
  
    private Date fechaFin;

    private boolean activo;

    private Integer usuarioCreacion;

    private Date fechaCreacion;

    private Integer usuarioModificacion;
    
    private Date fechaModificacion;

    
}
