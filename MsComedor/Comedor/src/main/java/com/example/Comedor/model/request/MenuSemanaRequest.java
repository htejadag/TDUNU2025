package com.example.Comedor.model.request;

import java.sql.Date;

import lombok.Data;

@Data
public class MenuSemanaRequest {

    private Date fechaInicio;
  
    private Date fechaFin;

    private boolean activo;

}
