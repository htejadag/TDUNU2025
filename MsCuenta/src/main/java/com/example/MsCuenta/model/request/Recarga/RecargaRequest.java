package com.example.MsCuenta.model.request.Recarga;

import lombok.Data;

@Data
public class RecargaRequest {
    
    private Integer idCuentaUsuario;

    private Integer idMetodoPago;

    private double monto;

    private boolean activo;

    private Integer usuarioCreacion;
}
