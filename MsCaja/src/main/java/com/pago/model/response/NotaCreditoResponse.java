package com.pago.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NotaCreditoResponse {
    private Integer notacreditoid;
    private Double monto;
    private String observacion;
    private String usuariocreacion;
    private LocalDateTime fechacreacion;
    private String usuariomodificacion;
    private LocalDateTime fechamodificacion;
    private Boolean activo;
    private Boolean eseliminado;
    private Integer devolucionid;
}
