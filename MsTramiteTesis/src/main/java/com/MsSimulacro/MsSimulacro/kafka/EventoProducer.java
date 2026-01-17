package com.MsSimulacro.MsSimulacro.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventoProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public EventoProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviar(String mensaje) {
        kafkaTemplate.send("ms-eventos", mensaje);
    }
}
