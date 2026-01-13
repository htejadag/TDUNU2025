package com.example.MsCuenta.model.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RecargaResponse {

    private Integer id;

    private Integer idCuentaUsuario;

    private Integer idMetodoPago;

    private double monto;

    private String fechaRecarga;

    private Integer usuarioCreacion;

    private LocalDate fechaCreacion;

    private Integer usuarioModificacion;

    private LocalDate fechaModificacion;



}
