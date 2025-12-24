package com.unu.ms.MsConsejo.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;



@Component
public class KafkaProducerPublisher {

    @Value("${spring.kafka.template.default-topic}")
    String topicName;

    @Autowired
     KafkaTemplate<Integer, String> kafkaTemplate;

    public void sendMessage(String mensaje) {
        System.out.println("Producing message: " + mensaje);

        kafkaTemplate.send(topicName, mensaje);
    }
}
