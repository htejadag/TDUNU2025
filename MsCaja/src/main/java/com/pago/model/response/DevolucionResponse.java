package com.pago.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DevolucionResponse {
    private Integer devolucionid;
    private LocalDateTime fecha;
    private Double montodevuelto;
    private String observaciones;
    private Integer pagoid;
    private String usuariocreacion;
    private LocalDateTime fechacreacion;
    private String usuariomodificacion;
    private LocalDateTime fechamodificacion;
    private Boolean activo;
    private Boolean eseliminado;
}
