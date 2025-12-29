package com.example.MsCuenta.model.response;
import java.time.LocalDate;

import lombok.Data;

@Data
public class MovimientoResponse {

    private Integer id;

    private Integer id_cuenta_usuario;

    private Integer id_tipo_movimiento;

    private Integer id_operacion;

    private Integer monto;

    private LocalDate fecha_movimiento;

    private Integer usuarioCreacion;

    private LocalDate fechaCreacion;

    private Integer usuarioModificacion;

    private LocalDate fechaModificacion;
}
