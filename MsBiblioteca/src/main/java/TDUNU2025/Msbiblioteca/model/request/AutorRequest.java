package TDUNU2025.Msbiblioteca.model.request;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorRequest {

    private Integer idPersona; 

    private String biografia;

    private LocalDate fechaFallecimiento;

    private String fotoUrl;
}