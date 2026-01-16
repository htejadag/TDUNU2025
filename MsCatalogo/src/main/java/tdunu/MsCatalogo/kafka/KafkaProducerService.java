package tdunu.MsCatalogo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tdunu.MsCatalogo.config.KafkaConfig;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void enviarCatalogoActualizado(String accion, Integer catalogoId, String nombre) {
        Map<String, Object> mensaje = new HashMap<>();
        mensaje.put("accion", accion);
        mensaje.put("catalogoId", catalogoId);
        mensaje.put("nombre", nombre);
        mensaje.put("timestamp", LocalDateTime.now().toString());
        mensaje.put("origen", "MsCatalogo");

        log.info("Enviando mensaje a Kafka - Topic: {} - Mensaje: {}", 
                KafkaConfig.TOPIC_CATALOGO_ACTUALIZADO, mensaje);
        
        kafkaTemplate.send(KafkaConfig.TOPIC_CATALOGO_ACTUALIZADO, mensaje);
        
        log.info("Mensaje enviado exitosamente a Kafka");
    }

    public void enviarMensaje(String topic, Object mensaje) {
        log.info("Enviando mensaje a Kafka - Topic: {} - Mensaje: {}", topic, mensaje);
        kafkaTemplate.send(topic, mensaje);
    }
}
