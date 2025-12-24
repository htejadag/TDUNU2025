package com.service.MsTramiteTesis.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Evento de Proyecto para publicar en Kafka
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProyectoEvento {

    private String eventoId;
    private String proyectoId;
    private String tipoEvento; // CREADO, ACTUALIZADO, ELIMINADO, ESTADO_CAMBIADO
    private String estadoAnterior;
    private String estadoNuevo;
    private String titulo;
    private String descripcion;
    private LocalDateTime timestamp;
}
