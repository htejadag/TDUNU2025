package tdunu2025.Msbiblioteca.model.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AutorResponse {
    private Long idAutor;
    private Long idPersona;
    private String biografia;
    private LocalDate fechaFallecimiento;
    private String fotoUrl;
}