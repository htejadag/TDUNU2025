package pe.unu.MsNotificacionesPosgrado.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaNotificationListener {

    @KafkaListener(topics = "solicitudes-events", groupId = "notificaciones-group")
    public void listen(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(message);

            String tipo = rootNode.path("tipoSolicitud").asText("DESCONOCIDO");
            String estado = rootNode.path("estadoSolicitud").asText("DESCONOCIDO");
            String descripcion = rootNode.path("descripcion").asText("Sin descripción");
            String fecha = rootNode.path("fechaRegistro").asText("Sin fecha");
            int idSolicitud = rootNode.path("idSolicitud").asInt(-1);

            System.out.println("------------------------------------------------------");
            System.out.println(" >>> NOTIFICACION: Nueva Solicitud Recibida");
            System.out.println(" >>> ID: " + idSolicitud);
            System.out.println(" >>> TIPO: " + tipo);
            System.out.println(" >>> ESTADO: " + estado);
            System.out.println(" >>> DESCRIPCION: " + descripcion);
            System.out.println(" >>> FECHA: " + fecha);
            System.out.println("------------------------------------------------------");

        } catch (Exception e) {
            System.err.println("Error procesando mensaje Kafka: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
