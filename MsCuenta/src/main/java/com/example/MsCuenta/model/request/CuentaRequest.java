package com.example.MsCuenta.model.request;

import lombok.Data;

@Data
public class CuentaRequest {
    private Integer idUsuarioRol;
    private double saldo;
    private boolean activo;
    // No pedimos fechas ni usuarios aquí, eso lo manejamos en el backend
}