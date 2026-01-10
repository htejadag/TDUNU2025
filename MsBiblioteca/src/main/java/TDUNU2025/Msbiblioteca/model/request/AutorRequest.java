package tdunu2025.msbiblioteca.model.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AutorRequest {
    private Long idPersona;
    private String biografia;
    private LocalDate fechaFallecimiento;
    private String fotoUrl;
}