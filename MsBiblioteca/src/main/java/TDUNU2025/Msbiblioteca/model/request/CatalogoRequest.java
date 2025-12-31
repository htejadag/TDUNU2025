package TDUNU2025.Msbiblioteca.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogoRequest {
    
    private String nombre;
    
    private String descripcion;
    
    private Integer estado; 
}