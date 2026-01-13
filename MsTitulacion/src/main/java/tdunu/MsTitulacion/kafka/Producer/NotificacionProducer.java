package tdunu.MsTitulacion.kafka.producer;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tdunu.MsTitulacion.kafka.event.NotificacionEvent;

import org.springframework.kafka.core.KafkaTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificacionProducer {
    
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOPIC = "notificacion-event";

    public void publish(NotificacionEvent event){
        try{
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, json);
            log.info("Evento enviado a Kafka: {}", TOPIC);
        }catch (Exception e){
            log.error("Error publicando en Kafka: {}", e.getMessage());
        }
    }

}
