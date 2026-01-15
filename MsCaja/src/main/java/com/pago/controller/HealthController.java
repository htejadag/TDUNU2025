package com.pago.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
public class HealthController {

    private static final String STATUS_KEY = "status";
    private static final String ERROR_KEY = "error";
    private static final String STATUS_UP = "UP";
    private static final String STATUS_DOWN = "DOWN";

    private final RedisTemplate<String, Object> redisTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/redis")
    public Map<String, Object> checkRedis() {
        Map<String, Object> response = new HashMap<>();
        String testKey = "health-check-" + UUID.randomUUID();
        String testValue = "ok";

        try {
            // Write
            redisTemplate.opsForValue().set(testKey, testValue);
            // Read
            Object readValue = redisTemplate.opsForValue().get(testKey);
            // Delete
            Boolean deleted = redisTemplate.delete(testKey);

            response.put(STATUS_KEY, STATUS_UP);
            response.put("operation", "SET/GET/DEL");
            response.put("connection", "Successful");
            response.put("verified", testValue.equals(readValue) && Boolean.TRUE.equals(deleted));
        } catch (Exception e) {
            log.error("Redis health check failed", e);
            response.put(STATUS_KEY, STATUS_DOWN);
            response.put(ERROR_KEY, e.getMessage());
        }
        return response;
    }

    @GetMapping("/kafka")
    public Map<String, Object> checkKafka() {
        Map<String, Object> response = new HashMap<>();
        String topic = "test-topic";
        String message = "test-message-" + UUID.randomUUID();

        try {
            kafkaTemplate.send(topic, message).get(); // .get() waits for the result to ensure connection
            response.put(STATUS_KEY, STATUS_UP);
            response.put("topic", topic);
            response.put("message_sent", message);
            response.put("connection", "Successful");
        } catch (InterruptedException e) {
            log.error("Kafka health check interrupted", e);
            Thread.currentThread().interrupt(); // Restore interrupted status
            response.put(STATUS_KEY, STATUS_DOWN);
            response.put(ERROR_KEY, "Interrupted during message send: " + e.getMessage());
        } catch (Exception e) {
            log.error("Kafka health check failed", e);
            response.put(STATUS_KEY, STATUS_DOWN);
            response.put(ERROR_KEY, e.getMessage());
        }
        return response;
    }
}
