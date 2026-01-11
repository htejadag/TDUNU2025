package TDUNU2025.Msbiblioteca.service.kafka;

import TDUNU2025.Msbiblioteca.model.entity.DetalleUsuario;
import TDUNU2025.Msbiblioteca.model.event.UsuarioEvent;
import TDUNU2025.Msbiblioteca.service.DetalleUsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private static final String TOPIC_USUARIOS = "biblioteca.usuarios.actualizaciones";
    private static final String GROUP_ID = "ms-biblioteca"; // Asegúrate que coincida con application.properties
    private static final String EVENTO_USUARIO_NUEVO = "USUARIO_NUEVO";

    private final DetalleUsuarioService detalleUsuarioService;
    private final ObjectMapper objectMapper; // Herramienta para convertir JSON manual

    // CAMBIO IMPORTANTE: Recibimos String, no UsuarioEvent
    @KafkaListener(topics = TOPIC_USUARIOS, groupId = GROUP_ID)
    public void escucharEventosUsuarios(String mensajeJson) {

        log.info("📩 Kafka recibió mensaje crudo: {}", mensajeJson);

        try {
            // 1. Conversión Manual (String -> Objeto)
            UsuarioEvent event = objectMapper.readValue(mensajeJson, UsuarioEvent.class);

            // 2. Validación básica
            if (event == null) {
                log.warn("El evento convertido es NULL.");
                return;
            }

            log.info("🔄 Evento procesado -> Tipo: {}, UsuarioID: {}", 
                     event.getTipoEvento(), event.getIdUsuario());

            // 3. Lógica de Negocio
            if (EVENTO_USUARIO_NUEVO.equals(event.getTipoEvento())) {
                procesarNuevoUsuario(event);
            } else {
                log.debug("Evento ignorado: {}", event.getTipoEvento());
            }

        } catch (JsonProcessingException e) {
            log.error("❌ Error al convertir el JSON recibido: {}", e.getMessage());
        } catch (Exception e) {
            log.error("❌ Error inesperado: ", e);
        }
    }

    private void procesarNuevoUsuario(UsuarioEvent event) {
        if (event.getIdUsuario() == null) {
            log.error("Evento sin ID de usuario.");
            return;
        }

        // Idempotencia: Si ya existe, no hacemos nada
        if (detalleUsuarioService.existePorIdUsuario(event.getIdUsuario())) {
            log.warn("El usuario ID {} ya existe. Se omite.", event.getIdUsuario());
            return;
        }

        // Crear registro
        DetalleUsuario nuevoDetalle = DetalleUsuario.builder()
                .idUsuario(event.getIdUsuario())
                .totalPrestamos(0)
                .totalMultas(0)
                .build();

        detalleUsuarioService.guardar(nuevoDetalle);
        log.info("✅ ¡ÉXITO! DetalleUsuario creado para ID: {}", event.getIdUsuario());
    }
}