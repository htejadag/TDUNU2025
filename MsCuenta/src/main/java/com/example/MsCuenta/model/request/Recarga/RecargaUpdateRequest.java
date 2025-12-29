package com.example.MsCuenta.model.request.Recarga;



import lombok.Data;

@Data
public class RecargaUpdateRequest {
    
    private Integer id_cuenta_usuario;

    private String metodo_pago;

    private String referencia;

    private Integer monto;

    private boolean activo;

    private Integer usuarioModificacion;

}