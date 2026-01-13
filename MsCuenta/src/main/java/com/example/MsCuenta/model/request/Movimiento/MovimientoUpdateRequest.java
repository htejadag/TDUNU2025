package com.example.MsCuenta.model.request.Movimiento;


import lombok.Data;

@Data
public class MovimientoUpdateRequest {
   
    private Integer idCuentaUsuario;

    private Integer idTipoMovimiento;
    
    private Integer monto;

    private boolean activo;

    private Integer usuarioModificacion;
}
