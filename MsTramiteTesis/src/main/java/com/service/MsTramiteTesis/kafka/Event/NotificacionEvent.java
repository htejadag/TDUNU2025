package com.service.MsTramiteTesis.kafka.Event;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionEvent {

    private String idUsuario;

    private String mensaje;

    private LocalDateTime fechaCreacion;
    
}
