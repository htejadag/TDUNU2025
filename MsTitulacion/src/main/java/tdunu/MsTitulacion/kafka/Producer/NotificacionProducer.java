package tdunu.MsTitulacion.kafka.Producer;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import tdunu.MsTitulacion.kafka.Event.NotificacionEvent;
import org.springframework.kafka.core.KafkaTemplate;

@Component
@RequiredArgsConstructor
public class NotificacionProducer {
    
    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOPIC = "notificacion-topic";

    public void publish(NotificacionEvent event){
        try{
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, json);
        }catch (Exception e){
            System.err.println("Kafka audit error: " + e.getMessage());
        }
    }

}
