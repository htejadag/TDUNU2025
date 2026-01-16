package com.pago.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/send")
    public Map<String, Object> sendMessage(@RequestParam String message) {
        Map<String, Object> response = new HashMap<>();
        String topic = "test-topic";
        try {
            log.info("Sending message to Kafka: topic={}, message={}", topic, message);
            kafkaTemplate.send(topic, message).get();
            response.put("status", "SUCCESS");
            response.put("topic", topic);
            response.put("message_sent", message);
        } catch (Exception e) {
            log.error("Failed to send message to Kafka", e);
            response.put("status", "ERROR");
            response.put("error", e.getMessage());
        }
        return response;
    }
}
