package com.MsExamen.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventoConsumer {

    @KafkaListener(topics = "ms-eventos", groupId = "grupo-ms-b")
    public void recibir(String mensaje) {
        System.out.println("✅ MS B recibió: " + mensaje);
    }
}
