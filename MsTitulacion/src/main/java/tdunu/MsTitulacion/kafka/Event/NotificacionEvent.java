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
    
    private String microservicio;    // MsTitulacion
    private String modulo;           // Titulacion
    private String operacion;        // CREATE | UPDATE
    private String entidad;          // Dictamen
    private String idEntidad;           
    private Object datos;            // El objeto DictamenResponse
    private String usuario;          // ID o username del que opera
    private LocalDateTime fechaEvento;

}
