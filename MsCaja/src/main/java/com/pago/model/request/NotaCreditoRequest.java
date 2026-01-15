package com.pago.model.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NotaCreditoRequest {
    private Integer notaCreditoId;
    private Double monto;
    private String observacion;
    private String usuarioCreacion;
    private LocalDateTime fechaCreacion;
    private String usuarioModificacion;
    private LocalDateTime fechaModificacion;
    private Boolean activo;
    private Boolean esEliminado;
    private Integer devolucionId;
}
