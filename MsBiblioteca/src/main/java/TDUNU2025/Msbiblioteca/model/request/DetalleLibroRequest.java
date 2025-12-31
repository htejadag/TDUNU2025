package TDUNU2025.Msbiblioteca.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleLibroRequest {

    private Long idLibro;         
    
    private Integer stockTotal;
    
    private Integer stockDisponible;
    
    private String ubicacionFisica;
}