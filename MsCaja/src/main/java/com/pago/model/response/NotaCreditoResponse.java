package com.pago.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NotaCreditoResponse {
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
