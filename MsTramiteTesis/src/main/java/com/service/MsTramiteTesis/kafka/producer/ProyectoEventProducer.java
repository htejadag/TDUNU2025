package com.service.MsTramiteTesis.kafka.producer;

import com.service.MsTramiteTesis.kafka.event.ProyectoEvento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Producer de eventos de proyecto para Kafka
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProyectoEventProducer {

    private static final String TOPIC = "proyecto-eventos";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Publicar evento cuando se crea un proyecto
     */
    public void publicarProyectoCreado(String proyectoId, String titulo, String descripcion, String estado) {
        ProyectoEvento evento = ProyectoEvento.builder()
                .eventoId(UUID.randomUUID().toString())
                .proyectoId(proyectoId)
                .tipoEvento("CREADO")
                .estadoNuevo(estado)
                .titulo(titulo)
                .descripcion(descripcion)
                .timestamp(LocalDateTime.now())
                .build();

        enviarEvento(evento);
    }

    /**
     * Publicar evento cuando se actualiza un proyecto
     */
    public void publicarProyectoActualizado(String proyectoId, String estadoAnterior, String estadoNuevo) {
        ProyectoEvento evento = ProyectoEvento.builder()
                .eventoId(UUID.randomUUID().toString())
                .proyectoId(proyectoId)
                .tipoEvento("ESTADO_CAMBIADO")
                .estadoAnterior(estadoAnterior)
                .estadoNuevo(estadoNuevo)
                .timestamp(LocalDateTime.now())
                .build();

        enviarEvento(evento);
    }

    /**
     * Publicar evento cuando se elimina un proyecto
     */
    public void publicarProyectoEliminado(String proyectoId) {
        ProyectoEvento evento = ProyectoEvento.builder()
                .eventoId(UUID.randomUUID().toString())
                .proyectoId(proyectoId)
                .tipoEvento("ELIMINADO")
                .timestamp(LocalDateTime.now())
                .build();

        enviarEvento(evento);
    }

    /**
     * Método privado para enviar eventos a Kafka
     */
    private void enviarEvento(ProyectoEvento evento) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC, evento.getProyectoId(),
                evento);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("✅ Evento enviado exitosamente: {} al topic: {} en partition: {}",
                        evento.getTipoEvento(),
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition());
            } else {
                log.error("❌ Error al enviar evento: {}", evento.getTipoEvento(), ex);
            }
        });
    }
}
