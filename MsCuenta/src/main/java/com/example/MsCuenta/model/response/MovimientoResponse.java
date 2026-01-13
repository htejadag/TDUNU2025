package com.example.MsCuenta.model.response;
import java.time.LocalDate;

import com.example.MsCuenta.model.entity.CatalogoModel;
import com.example.MsCuenta.model.entity.CuentaUsuarioModel;

import lombok.Data;

@Data
public class MovimientoResponse {

    private Integer id;

    private CuentaUsuarioModel idCuentaUsuario;

    private CatalogoModel idTipoMovimiento;

    private Integer monto;

    private LocalDate fechaMovimiento;

    private boolean activo;

    private Integer usuarioCreacion;

    private LocalDate fechaCreacion;

    private Integer usuarioModificacion;

    private LocalDate fechaModificacion;
}
