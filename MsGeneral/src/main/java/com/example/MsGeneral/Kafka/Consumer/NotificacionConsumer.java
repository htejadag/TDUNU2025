package com.example.MsGeneral.Kafka.Consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.MsGeneral.Model.Entidad.Notificacion;
import com.example.MsGeneral.Repository.NotificacionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificacionConsumer {

    private final ObjectMapper objectMapper;
    private final NotificacionRepository notificacionRepository;

    @KafkaListener(
        topics = "nombretopic",
        groupId = "notificacion-consumer"
    )
    public void consumir(String mensaje){
        try {
            log.info("Mensaje recibido del ms productor: {}", mensaje);

            Notificacion notificacion = objectMapper.readValue(mensaje,Notificacion.class);
            notificacionRepository.save(notificacion);

            log.info("Notificación guardada: {} - {}",
                    notificacion.getMensaje(),
                    notificacion.getIdUsuario());

        } catch (Exception e) {
            log.error("Error procesando evento de auditoría", e);
        }
    }

}
