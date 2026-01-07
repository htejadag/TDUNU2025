package Postgrado.notificaciones.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @KafkaListener(topics = "postgrado_events", groupId = "notificaciones-group")
    public void listen(String message) {
        System.out.println("--------------------------------------------------");
        System.out.println("Mensaje recibido de Kafka: " + message);

        // Simulación sencilla de procesamiento: identificar evento
        if (message.contains("SolicitudRegistrada")) {
            System.out.println(">> EVENTO DETECTADO: Nueva Solicitud Registrada");
            // Aquí iría la lógica para enviar email o notificar
        } else if (message.contains("JuradoAsignado")) {
            System.out.println(">> EVENTO DETECTADO: Jurado Asignado a Expediente");
        }
        System.out.println("--------------------------------------------------");
    }
}
