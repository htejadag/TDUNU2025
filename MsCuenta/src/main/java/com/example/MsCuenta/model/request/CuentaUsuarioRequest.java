package com.example.MsCuenta.model.request;

import java.sql.Date;

import lombok.Data;

@Data
public class CuentaUsuarioRequest {
    
    private Integer idUsuarioRol;
    private double saldo;
    private boolean activo;

    private Integer usuarioCreacion;
    private Date fechaCreacion;
    private Integer usuarioModificacion;
    private Date fechaModificacion;

  
}