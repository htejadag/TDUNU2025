package com.example.MsCuenta.model.request.CuentaUsuario;

import lombok.Data;

@Data
public class CuentaUsuarioRequest {
    
    private Integer idUsuarioRol;

    private double saldo;
    
    private boolean activo;

    private Integer usuarioCreacion;

  
}