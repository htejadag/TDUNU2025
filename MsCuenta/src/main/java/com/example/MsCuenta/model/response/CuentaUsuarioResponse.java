package com.example.MsCuenta.model.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CuentaUsuarioResponse {

    private Integer id;
    
    private Integer idUsuarioRol;

    private double saldo;

    private boolean activo;

    private Integer usuarioCreacion;

    private LocalDate fechaCreacion;

    private Integer usuarioModificacion;

    private LocalDate fechaModificacion;

    


    
}
