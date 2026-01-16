package tdunu.MsAsistencia.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void enviarAsistenciaRegistrada(String accion, Integer asistenciaId, String descripcion) {
        Map<String, Object> mensaje = new HashMap<>();
        mensaje.put("accion", accion);
        mensaje.put("asistenciaId", asistenciaId);
        mensaje.put("descripcion", descripcion);
        mensaje.put("origen", "MsAsistencia");
        mensaje.put("timestamp", LocalDateTime.now().toString());

        log.info("Enviando mensaje a Kafka - Topic: asistencia-registrada - Mensaje: {}", mensaje);
        
        kafkaTemplate.send("asistencia-registrada", mensaje);
        
        log.info("Mensaje enviado exitosamente a Kafka");
    }
}
