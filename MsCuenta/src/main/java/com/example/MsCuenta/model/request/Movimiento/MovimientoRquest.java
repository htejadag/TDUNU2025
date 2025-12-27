package com.example.MsCuenta.model.request.Movimiento;

import java.sql.Date;

import lombok.Data;

@Data
public class MovimientoRquest {
    private Integer id_cuenta_usuario;

    private Integer id_tipo_movimiento;

    private Integer id_operacion;

    private Integer monto;
    
    private Date fecha_movimiento;

    private Integer usuarioCreacion;
}
