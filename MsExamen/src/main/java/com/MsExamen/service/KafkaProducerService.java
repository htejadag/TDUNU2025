package com.MsExamen.service;

import com.MsExamen.dto.KafkaEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEvent(String topic, String eventType, Object data) {
        KafkaEvent<Object> event = KafkaEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType(eventType)
                .timestamp(LocalDateTime.now())
                .data(data)
                .build();

        log.info("Sending event {} to topic {}", eventType, topic);
        kafkaTemplate.send(topic, event);
    }
}
