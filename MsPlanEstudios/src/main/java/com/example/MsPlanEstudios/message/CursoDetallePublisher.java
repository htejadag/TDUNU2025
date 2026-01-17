package com.example.MsPlanEstudios.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.MsPlanEstudios.model.events.cursoDetalleEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CursoDetallePublisher {

    @Value("${app.kafka.topic.curso-detalle-events}")
    private String topic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(cursoDetalleEvent event) {
        try {
            kafkaTemplate.send(topic, String.valueOf(event.getIdDetalleCurso()), event);
            log.info("Evento curso-detalle enviado a Kafka: {}", event);
        } catch (Exception e) {
            log.error("Error al enviar evento a Kafka: {}", e.getMessage());
        }
    }
}
