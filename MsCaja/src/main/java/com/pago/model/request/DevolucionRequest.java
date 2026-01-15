package com.pago.model.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DevolucionRequest {
    private Integer devolucionId;
    private LocalDateTime fecha;
    private Double montoDevuelto;
    private String observaciones;
    private Integer pagoId;
    private String usuarioCreacion;
    private LocalDateTime fechaCreacion;
    private String usuarioModificacion;
    private LocalDateTime fechaModificacion;
    private Boolean activo;
    private Boolean esEliminado;
}
