package com.example.MsGeneral.Model.Response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NotificacionResponse {
    private String idNotificacion;
    private String mensaje;
    private boolean leido;
    private LocalDateTime fechaCreacion;
}
