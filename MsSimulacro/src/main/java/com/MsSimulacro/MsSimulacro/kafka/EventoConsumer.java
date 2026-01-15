package com.MsSimulacro.MsSimulacro.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventoConsumer {

    @KafkaListener(topics = "ms-eventos-pc1", groupId = "grupo-pc1")
    public void recibir(String mensaje) {
        System.out.println(" MS Simulacro recibió: " + mensaje);
    }
}
