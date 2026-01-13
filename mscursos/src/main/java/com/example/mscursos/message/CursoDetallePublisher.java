package com.example.mscursos.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.mscursos.dto.CursoDetalleEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CursoDetallePublisher {

    @Value("${app.kafka.topic.curso-detalle-events}")
    private String topic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(CursoDetalleEvent event) {
        String key = String.valueOf(event.getIdCurso());
        kafkaTemplate.send(topic, key, event);
        log.info("EVENTO ENVIADO A KAFKA [{}] -> {}", topic, event);
    }
}
