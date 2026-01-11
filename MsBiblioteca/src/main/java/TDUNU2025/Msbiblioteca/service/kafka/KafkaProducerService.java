package TDUNU2025.Msbiblioteca.service.kafka;

import TDUNU2025.Msbiblioteca.model.event.PrestamoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private static final String TOPIC_PRESTAMOS = "biblioteca.prestamos.eventos";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Envía un evento de préstamo al ecosistema.
     *
     * @param event Objeto con la información del préstamo
     */
    public void enviarEventoPrestamo(PrestamoEvent event) {

        if (event == null) {
            log.warn("Intento de enviar evento de préstamo NULL");
            return;
        }

        kafkaTemplate.send(TOPIC_PRESTAMOS, event);

        log.info("Evento de préstamo enviado a Kafka -> {}",
                event.getTipoEvento());
    }

    /**
     * Método genérico para enviar cualquier tipo de evento.
     *
     * @param topic   Tópico Kafka
     * @param mensaje Evento a enviar
     */
    public void enviarEventoGenerico(String topic, Object mensaje) {

        if (topic == null || topic.isBlank()) {
            log.warn("Tópico Kafka inválido");
            return;
        }

        if (mensaje == null) {
            log.warn("Mensaje Kafka NULL para el tópico {}", topic);
            return;
        }

        kafkaTemplate.send(topic, mensaje);

        log.debug("Mensaje enviado a Kafka al tópico {}", topic);
    }
}
