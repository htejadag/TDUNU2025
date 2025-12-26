package com.microservice.MsCatalogoTesis.kafka.consumer;

import com.microservice.MsCatalogoTesis.kafka.event.ProyectoEvento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProyectoEventConsumer {

    @KafkaListener(topics = "proyecto-eventos", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consumirProyectoEvento(ProyectoEvento evento) {
        log.info("Evento recibido - Tipo: {}, ProyectoId: {}, Timestamp: {}",
                evento.getTipoEvento(),
                evento.getProyectoId(),
                evento.getTimestamp());

        try {
            switch (evento.getTipoEvento()) {
                case "CREADO":
                    procesarProyectoCreado(evento);
                    break;
                case "ESTADO_CAMBIADO":
                    procesarEstadoCambiado(evento);
                    break;
                case "ELIMINADO":
                    procesarProyectoEliminado(evento);
                    break;
                default:
                    log.warn("Tipo de evento desconocido: {}", evento.getTipoEvento());
            }
        } catch (Exception e) {
            log.error("Error procesando evento: {}", evento.getEventoId(), e);
        }
    }

    private void procesarProyectoCreado(ProyectoEvento evento) {
        log.info("Procesando proyecto creado: {} - Estado: {}",
                evento.getTitulo(),
                evento.getEstadoNuevo());
    }

    private void procesarEstadoCambiado(ProyectoEvento evento) {
        log.info("Procesando cambio de estado: {} -> {}",
                evento.getEstadoAnterior(),
                evento.getEstadoNuevo());
    }

    private void procesarProyectoEliminado(ProyectoEvento evento) {
        log.info("Procesando proyecto eliminado: {}", evento.getProyectoId());
    }
}
