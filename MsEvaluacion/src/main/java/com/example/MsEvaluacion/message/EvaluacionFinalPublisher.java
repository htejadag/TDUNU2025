package com.example.MsEvaluacion.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.MsEvaluacion.model.events.EvaluacionFinalRegistradaEvent;

@Service
public class EvaluacionFinalPublisher {

    @Value("${app.kafka.topic.evaluacion-final-events}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(EvaluacionFinalRegistradaEvent event) {
        String key = String.valueOf(event.getIdCursoDetalle());
        kafkaTemplate.send(topic, key, event);
    }
}
