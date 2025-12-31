package TDUNU2025.Msbiblioteca.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoEvent {
    
    // Identificador del tipo de acción para que otros equipos sepan qué pasó
    // Ejemplos: "PRESTAMO_CREADO", "PRESTAMO_DEVUELTO"
    private String tipoEvento;
    
    // Datos clave que viajan en el mensaje JSON
    private Integer idPrestamo;
    private Integer idUsuario;
    private Integer idLibro;
    
    // Estampilla de tiempo del evento
    private LocalDateTime fechaEvento;
    
    // Información adicional opcional para el ecosistema
    private String mensaje;
}