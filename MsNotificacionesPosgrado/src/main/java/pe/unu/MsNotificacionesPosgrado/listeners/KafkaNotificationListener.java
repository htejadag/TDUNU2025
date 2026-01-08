package pe.unu.MsNotificacionesPosgrado.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaNotificationListener {

    @KafkaListener(topics = "solicitudes-events", groupId = "notificaciones-group")
    public void listen(String message) {
        System.out.println("Solicitud Creada: " + message);
        // podriamos deserializar el JSON y guardar en base de datos o enviar un
        // correo
    }
}
