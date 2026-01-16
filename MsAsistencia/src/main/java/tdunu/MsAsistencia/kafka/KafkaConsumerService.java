package tdunu.MsAsistencia.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "catalogo-actualizado", groupId = "asistencia-group")
    public void consumirCatalogoActualizado(Map<String, Object> mensaje) {
        log.info("========================================");
        log.info("MENSAJE RECIBIDO DE KAFKA");
        log.info("Topic: catalogo-actualizado");
        log.info("Contenido: {}", mensaje);
        log.info("Accion: {}", mensaje.get("accion"));
        log.info("CatalogoId: {}", mensaje.get("catalogoId"));
        log.info("Nombre: {}", mensaje.get("nombre"));
        log.info("========================================");
    }
}