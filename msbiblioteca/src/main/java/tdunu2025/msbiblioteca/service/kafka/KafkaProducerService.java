package tdunu2025.msbiblioteca.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tdunu2025.msbiblioteca.model.event.PrestamoEvent;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private static final String TOPIC_PRESTAMOS = "biblioteca.prestamos.eventos";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Envía un evento de préstamo al ecosistema.
     * @param event Objeto con la información del préstamo
     */
    public void enviarEventoPrestamo(PrestamoEvent event) {

        if (event == null) {
            log.warn("Intento de enviar evento de préstamo NULL");
            return;
        }

        try {
            kafkaTemplate.send(TOPIC_PRESTAMOS, event);

            log.info("Evento de préstamo enviado a Kafka -> Tipo: {}, LibroID: {}",
                    event.getTipoEvento(), event.getIdLibro());

        } catch (Exception e) {
            log.error("Error al enviar mensaje a Kafka: {}", e.getMessage());
        }
    }

    /**
     * Método genérico para enviar cualquier tipo de evento si lo necesitas a futuro.
     */
    public void enviarEventoGenerico(String topic, Object mensaje) {
        if (topic == null || topic.isBlank() || mensaje == null) {
            log.warn("Datos inválidos para envío Kafka");
            return;
        }
        kafkaTemplate.send(topic, mensaje);
    }
}