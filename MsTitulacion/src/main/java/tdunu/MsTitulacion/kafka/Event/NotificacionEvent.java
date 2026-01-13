package tdunu.MsTitulacion.kafka.event;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionEvent {
    
     private String idUsuario;

    private String mensaje;

    private LocalDateTime fechaCreacion;
}
