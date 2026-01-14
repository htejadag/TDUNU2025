package com.example.Comedor.message;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.Comedor.model.request.consumoRacion.KafkaEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConsumoMessagePublish {

    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final String topicName;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ConsumoMessagePublish(
            KafkaTemplate<Integer, String> kafkaTemplate,
            @Value("${spring.kafka.template.default-topic}") String topicName) {

        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void sendConsumoEvent(KafkaEvent event) throws JsonProcessingException {

        String value = objectMapper.writeValueAsString(event);
        log.info("Se envió mensaje por Kafka: {}", value);
        kafkaTemplate.send(topicName, value);
    }

}
