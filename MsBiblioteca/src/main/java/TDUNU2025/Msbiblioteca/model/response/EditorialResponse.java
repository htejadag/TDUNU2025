package TDUNU2025.Msbiblioteca.model.response;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditorialResponse {

    private Long idEditorial;
    
    private String nombre;
    
    private String direccion;
    
    private String telefono;
    
    private String email;
    
    private String sitioWeb;

    private LocalDateTime fechaRegistro;

    private String pais;
}