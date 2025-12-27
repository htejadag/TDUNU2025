package com.example.MsCuenta.model.request.CuentaUsuario;

import lombok.Data;

@Data
public class CuentaUsuarioUpdateRequest {
    
    private Integer idUsuarioRol;

    private double saldo;
    
    private boolean activo;

    private Integer usuarioModificacion;
}
