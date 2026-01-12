package com.example.MsAuditoria.Kafka.Consumer;

import com.example.MsAuditoria.Model.Entity.Auditoria;
import com.example.MsAuditoria.Repository.AuditoriaRepository;
import com.example.MsAuditoria.Util.KafkaTopics;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuditoriaConsumer {

    private final ObjectMapper objectMapper;
    private final AuditoriaRepository auditoriaRepository;

    @KafkaListener(
        topics = KafkaTopics.TOPICAUDITORIA,
        groupId = "auditoria-consumer"
    )
    public void consumir(String mensaje) {
        try {
            log.info("Mensaje recibido en MsAuditoria: {}", mensaje);

            Auditoria auditoria = objectMapper.readValue(mensaje, Auditoria.class);
            auditoriaRepository.save(auditoria);

            log.info("Auditoría guardada: {} - {}",
                    auditoria.getEntidad(),
                    auditoria.getOperacion());

        } catch (Exception e) {
            log.error("Error procesando evento de auditoría", e);
        }
    }
}
