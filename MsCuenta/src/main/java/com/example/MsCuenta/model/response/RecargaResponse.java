package com.example.MsCuenta.model.response;

import lombok.Data;

@Data
public class RecargaResponse {
    private Integer id;
    private Integer id_cuenta_usuario;
    private String metodo_pago;
    private String referencia;
    private String monto;
    private String fecha_recarga;

}
