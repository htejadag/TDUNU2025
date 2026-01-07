package tdunu.MsTitulacion.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Evento de Titulación para publicar en Kafka
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TitulacionEvento {

    private String eventoId;
    private String titulacionId;
    private String tipoEvento; // CREADO, ACTUALIZADO, ELIMINADO, ESTADO_CAMBIADO
    private String estadoAnterior;
    private String estadoNuevo;
    private String titulo;
    private String descripcion;
    private LocalDateTime timestamp;
}
