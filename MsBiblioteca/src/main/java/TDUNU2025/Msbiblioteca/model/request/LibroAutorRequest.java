package TDUNU2025.Msbiblioteca.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroAutorRequest {

    private Long idLibro;   
    
    private Integer idAutor; 
    
    private String rol;    
}