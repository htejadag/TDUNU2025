package com.service.MsTramiteTesis.kafka.Producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.MsTramiteTesis.kafka.Event.NotificacionEvent;
import com.service.MsTramiteTesis.util.KafkaTopics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificacionProducer {

    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOPIC = KafkaTopics.TOPICNOTIFICACIONES;

    public void publish(NotificacionEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, json);
        } catch (Exception e) {
            System.err.println("Kafka notificacion error: " + e.getMessage());
        }
    }

}
