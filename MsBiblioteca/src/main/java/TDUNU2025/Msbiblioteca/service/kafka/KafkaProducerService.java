package TDUNU2025.Msbiblioteca.service.kafka;

import TDUNU2025.MsBiblioteca.model.event.PrestamoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    // KafkaTemplate es la herramienta de Spring para enviar mensajes
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Envía un evento de préstamo al ecosistema.
     * @param event El objeto con la información del préstamo.
     */
    public void enviarEventoPrestamo(PrestamoEvent event) {
        // "biblioteca.prestamos.eventos" es el tópico que definimos en KafkaConfig
        kafkaTemplate.send("biblioteca.prestamos.eventos", event);
        System.out.println("Mensaje enviado a Kafka: " + event.getTipoEvento());
    }
    
    /**
     * Método genérico para enviar cualquier tipo de evento si fuera necesario
     */
    public void enviarEventoGenerico(String topic, Object mensaje) {
        kafkaTemplate.send(topic, mensaje);
    }
}