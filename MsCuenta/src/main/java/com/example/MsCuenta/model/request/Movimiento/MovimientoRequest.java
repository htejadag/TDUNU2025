package com.example.MsCuenta.model.request.Movimiento;



import lombok.Data;

@Data
public class MovimientoRequest {

    private Integer idCuentaUsuario;

    private Integer idTipoMovimiento;

    private double monto;

    private boolean activo;

    private Integer usuarioCreacion;
}
