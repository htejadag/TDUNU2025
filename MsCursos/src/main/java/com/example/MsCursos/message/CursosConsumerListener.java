package com.example.mscursos.message;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.mscursos.dto.CursoEvent;
import com.example.mscursos.dto.CursoPayload;
import com.example.mscursos.service.CursoService;
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

    @KafkaListener(topics = "${app.kafka.topic.curso-events}", groupId = "ms-cursos-group")
    public void onMessage(ConsumerRecord<String, String> record) {

        try {
            CursoEvent event = objectMapper.readValue(
                    record.value(),
                    CursoEvent.class);

            CursoPayload curso = event.getCurso();

            if ("UPSERT".equals(event.getType())) {
                cursoService.upsertDesdeKafka(curso);
            }

            if ("DELETE".equals(event.getType())) {
                cursoService.deleteDesdeKafka(curso.getId());
            }

            log.info("Evento {} procesado correctamente, id={}",
                    event.getType(), curso.getId());

        } catch (Exception e) {
            log.error("Error procesando mensaje Kafka", e);
        }
    }

}
