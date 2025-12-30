package com.example.MsCursos.message;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.MsCursos.model.request.CursoRequest;
import com.example.MsCursos.service.CursoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CursosConsumerListener {

    private static final Logger log = LoggerFactory.getLogger(CursosConsumerListener.class);

    private final CursoService cursoService;
    private final ObjectMapper objectMapper;

    public CursosConsumerListener(CursoService cursoService, ObjectMapper objectMapper) {
        this.cursoService = cursoService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) {

        log.info("****************************************************************");
        log.info("Mensaje Kafka => key={} value={}", consumerRecord.key(), consumerRecord.value());

        try {
            CursoRequest data = objectMapper.readValue(consumerRecord.value(), CursoRequest.class);

            // ✅ lógica: guardar o actualizar curso en BD
            cursoService.upsertDesdeKafka(data);

            log.info("OK procesado => id={}, codigo={}", data.getId(), data.getCodigo());
        } catch (Exception e) {
            log.error("Error procesando mensaje Kafka: {}", consumerRecord.value(), e);
        }
    }
}
