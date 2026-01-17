package com.example.MsGeneral.Kafka.Producer;

import com.example.MsGeneral.Kafka.Event.AuditoriaEvent;
import com.example.MsGeneral.util.KafkaTopics;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditoriaProducer {

    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOPIC = KafkaTopics.TOPICAUDITORIA;

    public void publish(AuditoriaEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, json);
        } catch (Exception e) {
            System.err.println("Kafka audit error: " + e.getMessage());
        }
    }
}
