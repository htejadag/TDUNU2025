package com.example.Comedor.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Value("${spring.kafka.template.default-topic}")
    String topicName;

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

   
    
    ObjectMapper objectMapper =new ObjectMapper(); 

    public void sendConsumoEvent( KafkaEvent event) throws JsonProcessingException {

        String value = objectMapper.writeValueAsString(event);
        log.info("Se envió mensaje por Kafka: {}", value);
        kafkaTemplate.send(topicName, value);
    }
       
    
    
}
