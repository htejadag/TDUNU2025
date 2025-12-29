package com.example.MsCuenta.model.request.Movimiento;


import lombok.Data;

@Data
public class MovimientoUpdateRequest {
    private Integer id_cuenta_usuario;

    private Integer id_tipo_movimiento;

    private Integer id_operacion;
    
    private Integer monto;

    private boolean activo;

    private Integer usuarioModificacion;
}
