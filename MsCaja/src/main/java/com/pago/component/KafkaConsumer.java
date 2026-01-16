package com.pago.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test-topic", groupId = "mscaja-group")
    public void listen(String message) {
        log.info("--------------------------------------------------");
        log.info("KAFKA MESSAGE RECEIVED: {}", message);
        log.info("--------------------------------------------------");
        System.out.println("\n>>> MESAJE RECIBIDO DE KAFKA: " + message + "\n");
    }
}
