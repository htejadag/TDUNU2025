package TDUNU2025.Msbiblioteca.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEvent {
    
    // Este evento podrías recibirlo cuando otro equipo crea un Usuario
    private String tipoEvento; // "USUARIO_NUEVO", "USUARIO_ELIMINADO"
    
    private Integer idUsuario;
    private String nombre;
    private String correo;
    
    // Para que msBiblioteca sepa si debe activar el récord en DetalleUsuario
    private Integer estado; 
}