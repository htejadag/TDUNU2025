package TDUNU2025.Msbiblioteca.model.request;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrestamoRequest {

    private Integer idUsuario;

    private Long idLibro; 

    private LocalDate fechaVencimiento;


    private String observaciones;
}