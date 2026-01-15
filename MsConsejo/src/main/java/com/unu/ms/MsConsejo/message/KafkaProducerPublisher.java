package com.unu.ms.MsConsejo.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }
    
}

@Component
public class KafkaProducerPublisher {

    @Value("${spring.kafka.template.default-topic}")
    String topicName;

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void sendMessage(String mensaje) {
        System.out.println("Enviando mensaje al topic de Kafka: " + topicName);
        kafkaTemplate.send(topicName, mensaje);
    }

    public void sendSesionConsejoModel(Object model) {
        
        try {
            String json = objectMapper.writeValueAsString(model);

            System.out.println("Enviando mensaje al topic de Kafka: " + json);
            kafkaTemplate.send(topicName, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializando mensaje Kafka", e);
        }
    }
}
