package tdunu.MsTitulacion.kafka.producer;

import tdunu.MsTitulacion.kafka.event.TitulacionEvento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class TitulacionEventProducer {

    private static final String TOPIC = "titulacion-eventos";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publicarTitulacionCreada(String titulacionId, String titulo, String descripcion, String estado) {
        TitulacionEvento evento = TitulacionEvento.builder()
                .eventoId(UUID.randomUUID().toString())
                .titulacionId(titulacionId)
                .tipoEvento("CREADO")
                .estadoNuevo(estado)
                .titulo(titulo)
                .descripcion(descripcion)
                .timestamp(LocalDateTime.now())
                .build();

        enviarEvento(evento);
    }

    public void publicarTitulacionActualizada(String titulacionId, String estadoAnterior, String estadoNuevo) {
        TitulacionEvento evento = TitulacionEvento.builder()
                .eventoId(UUID.randomUUID().toString())
                .titulacionId(titulacionId)
                .tipoEvento("ESTADO_CAMBIADO")
                .estadoAnterior(estadoAnterior)
                .estadoNuevo(estadoNuevo)
                .timestamp(LocalDateTime.now())
                .build();

        enviarEvento(evento);
    }

    public void publicarTitulacionEliminada(String titulacionId) {
        TitulacionEvento evento = TitulacionEvento.builder()
                .eventoId(UUID.randomUUID().toString())
                .titulacionId(titulacionId)
                .tipoEvento("ELIMINADO")
                .timestamp(LocalDateTime.now())
                .build();

        enviarEvento(evento);
    }

    private void enviarEvento(TitulacionEvento evento) {
        String titulacionId = java.util.Objects.requireNonNull(evento.getTitulacionId(),
                "El titulacionId no puede ser null");

        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC, titulacionId,
                evento);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Evento enviado exitosamente: {} al topic: {} en partition: {}",
                        evento.getTipoEvento(),
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition());
            } else {
                log.error("Error al enviar evento: {}", evento.getTipoEvento(), ex);
            }
        });
    }
}
