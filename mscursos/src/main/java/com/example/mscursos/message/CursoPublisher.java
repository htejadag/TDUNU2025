package com.example.mscursos.message;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.mscursos.dto.CursoEvent;
import com.example.mscursos.dto.CursoPayload;
import com.example.mscursos.model.entity.CursoModel;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CursoPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${app.kafka.topic.curso-events}")
    private String topic;

    public void publishUpsert(CursoModel c) {
        CursoPayload payload = new CursoPayload();
        payload.setId(c.getId());
        payload.setNombre(c.getNombre());
        payload.setCodigo(c.getCodigo());
        payload.setIdCiclo(c.getIdCiclo());
        payload.setIdCarrera(c.getIdCarrera());
        payload.setCreditos(c.getCreditos());
        payload.setHorasTeoricas(c.getHorasTeoricas());
        payload.setHorasPracticas(c.getHorasPracticas());
        payload.setEstado(c.getEstado());

        CursoEvent event = new CursoEvent("UPSERT", Instant.now(), payload);
        kafkaTemplate.send(topic, String.valueOf(c.getId()), event);
    }

    public void publishDelete(Integer id) {
        CursoPayload payload = new CursoPayload();
        payload.setId(id);

        CursoEvent event = new CursoEvent("DELETE", Instant.now(), payload);
        kafkaTemplate.send(topic, String.valueOf(id), event);
    }
}
