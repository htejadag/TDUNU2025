package tdunu2025.msbiblioteca.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tdunu2025.msbiblioteca.model.entity.DetalleUsuario;
import tdunu2025.msbiblioteca.model.event.UsuarioEvent;
import tdunu2025.msbiblioteca.service.DetalleUsuarioService;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private static final String TOPIC_USUARIOS = "biblioteca.usuarios.actualizaciones"; // Ajusta según el topic real
    private static final String GROUP_ID = "ms-biblioteca"; 
    private static final String EVENTO_USUARIO_NUEVO = "USUARIO_NUEVO";

    private final DetalleUsuarioService detalleUsuarioService;
    private final ObjectMapper objectMapper; 

    @KafkaListener(topics = TOPIC_USUARIOS, groupId = GROUP_ID)
    public void escucharEventosUsuarios(String mensajeJson) {

        log.info("Kafka recibió mensaje crudo: {}", mensajeJson);

        try {
            UsuarioEvent event = objectMapper.readValue(mensajeJson, UsuarioEvent.class);

            if (event == null) {
                log.warn("El evento convertido es NULL.");
                return;
            }

            log.info("Evento procesado -> Tipo: {}, UsuarioID: {}", 
                     event.getTipoEvento(), event.getIdUsuario());

            if (EVENTO_USUARIO_NUEVO.equals(event.getTipoEvento())) {
                procesarNuevoUsuario(event);
            } else {
                log.debug("Evento ignorado: {}", event.getTipoEvento());
            }

        } catch (JsonProcessingException e) {
            log.error("Error al convertir el JSON recibido: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado: ", e);
        }
    }

    private void procesarNuevoUsuario(UsuarioEvent event) {
        if (event.getIdUsuario() == null) {
            log.error("Evento sin ID de usuario.");
            return;
        }

        if (detalleUsuarioService.existePorIdUsuario(event.getIdUsuario())) {
            log.warn("El usuario ID {} ya existe. Se omite.", event.getIdUsuario());
            return;
        }

        DetalleUsuario nuevoDetalle = DetalleUsuario.builder()
                .idUsuario(event.getIdUsuario())
                .totalPrestamos(0)
                .totalMultas(0) // O 0.0 si es Double
                .build();

        detalleUsuarioService.guardar(nuevoDetalle);
        log.info("¡ÉXITO! DetalleUsuario creado para ID: {}", event.getIdUsuario());
    }
}