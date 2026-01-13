package com.service.MsTramiteTesis.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.MsTramiteTesis.model.dto.NotificacionEventDTO;
import com.service.MsTramiteTesis.util.KafkaTopics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificacionProducer {

    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    /**
     * Envía un evento de notificación al topic de Kafka
     * 
     * @param evento El evento a enviar
     */
    public void enviarEvento(NotificacionEventDTO evento) {
        try {
            String mensaje = objectMapper.writeValueAsString(evento);

            kafkaTemplate.send(KafkaTopics.TOPIC, mensaje);

            log.info("Evento enviado a Kafka - Tipo: {}, Rol destinatario: {}, Mensaje: {}",
                    evento.getTipoEvento(),
                    evento.getRolDestinatario(),
                    evento.getMensaje());

        } catch (Exception e) {
            log.error("Error al enviar evento de notificación a Kafka", e);
            throw new RuntimeException("Error al enviar notificación", e);
        }
    }

    /**
     * Envía un evento de notificación de forma asíncrona (fire and forget)
     * No lanza excepción si falla
     * 
     * @param evento El evento a enviar
     */
    public void enviarEventoAsync(NotificacionEventDTO evento) {
        try {
            String mensaje = objectMapper.writeValueAsString(evento);

            kafkaTemplate.send(KafkaTopics.TOPIC, mensaje);

            log.info("Evento async enviado a Kafka - Tipo: {}", evento.getTipoEvento());

        } catch (Exception e) {
            log.error("Error al enviar evento async de notificación a Kafka (no crítico)", e);
            // No lanza excepción para no afectar el flujo principal
        }
    }
}
