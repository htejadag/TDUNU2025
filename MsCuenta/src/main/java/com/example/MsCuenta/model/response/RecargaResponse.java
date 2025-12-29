package com.example.MsCuenta.model.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RecargaResponse {

    private Integer id;

    private Integer id_cuenta_usuario;

    private String metodo_pago;

    private String referencia;

    private Integer monto;

    private String fecha_recarga;

    private Integer usuarioCreacion;

    private LocalDate fechaCreacion;

    private Integer usuarioModificacion;

    private LocalDate fechaModificacion;



}
