package tdunu.MsTitulacion.kafka.Event;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionEvent {
    
    private String microservicio;    // MsTitulacion
    private String modulo;           // 
    private String operacion;        // CREATE | UPDATE | DELETE
    private String entidad;
    private String idEntidad;           
    private Object datos;            // snapshot
    private String usuario;
    private LocalDateTime fechaEvento;

}
