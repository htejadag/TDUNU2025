package tdunu.MsCatalogo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tdunu.MsCatalogo.config.KafkaConfig;

import java.util.Map;

@Slf4j
@Service
public class KafkaConsumerService {

    /**
     * Escucha mensajes del topic "asistencia-registrada"
     * Este mensaje viene desde MsAsistencia cuando se registra una nueva asistencia
     */
    @KafkaListener(
        topics = KafkaConfig.TOPIC_ASISTENCIA_REGISTRADA, 
        groupId = "catalogo-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumirAsistenciaRegistrada(Map<String, Object> mensaje) {
        log.info("═══════════════════════════════════════════════════════════════");
        log.info("MENSAJE RECIBIDO DE KAFKA - Topic: {}", KafkaConfig.TOPIC_ASISTENCIA_REGISTRADA);
        log.info("Contenido: {}", mensaje);
        log.info("═══════════════════════════════════════════════════════════════");
        
        // Procesar el mensaje según la acción
        String accion = (String) mensaje.get("accion");
        
        switch (accion) {
            case "ASISTENCIA_REGISTRADA":
                procesarAsistenciaRegistrada(mensaje);
                break;
            case "ASISTENCIA_MASIVA":
                procesarAsistenciaMasiva(mensaje);
                break;
            default:
                log.warn("Acción no reconocida: {}", accion);
        }
    }

    private void procesarAsistenciaRegistrada(Map<String, Object> mensaje) {
        log.info("Procesando asistencia registrada individual");
        // Aquí puedes actualizar estadísticas, contadores, etc.
        Integer programacionId = (Integer) mensaje.get("programacionId");
        String sistemaOrigen = (String) mensaje.get("sistemaOrigen");
        
        log.info("Programación ID: {} - Sistema: {}", programacionId, sistemaOrigen);
    }

    private void procesarAsistenciaMasiva(Map<String, Object> mensaje) {
        log.info("Procesando asistencia masiva");
        Integer totalRegistros = (Integer) mensaje.get("totalRegistros");
        
        log.info("Total de registros procesados: {}", totalRegistros);
    }
}
