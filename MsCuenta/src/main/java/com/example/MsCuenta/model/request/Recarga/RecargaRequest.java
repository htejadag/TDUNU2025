package com.example.MsCuenta.model.request.Recarga;

import java.sql.Date;

import lombok.Data;

@Data
public class RecargaRequest {
    private Integer id_cuenta_usuario;
    private String metodo_pago;
    private String referencia;
    private Integer monto;
    private Date fecha_recarga;

    private Integer usuarioCreacion;
}
