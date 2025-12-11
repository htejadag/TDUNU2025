package com.example.MsCuenta.model.response;

import lombok.Data;

@Data
public class CuentaUsuarioResponse {

    private Integer id;
    private Integer idUsuarioRol;
    private double saldo;
    private boolean activo;


    
}
