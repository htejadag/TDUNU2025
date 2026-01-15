package pe.unu.MsNotificacionesPosgrado.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaNotificationListener {

    @KafkaListener(topics = "solicitudes-events", groupId = "notificaciones-group")
    public void listen(pe.unu.MsNotificacionesPosgrado.Dto.SolicitudEvent event) {
        try {
            System.out.println("------------------------------------------------------");
            System.out.println(" >>> NOTIFICACION: Nueva Solicitud Recibida");
            System.out.println(" >>> ID: " + event.getIdSolicitud());
            System.out.println(" >>> TIPO: " + event.getTipoSolicitud());
            System.out.println(" >>> ESTADO: " + event.getEstadoSolicitud());
            System.out.println(" >>> DESCRIPCION: " + event.getDescripcion());
            System.out.println(" >>> FECHA: " + event.getFechaRegistro());
            System.out.println("------------------------------------------------------");

        } catch (Exception e) {
            System.err.println("Error procesando mensaje Kafka: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
