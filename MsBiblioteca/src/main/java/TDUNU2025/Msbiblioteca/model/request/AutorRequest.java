package TDUNU2025.Msbiblioteca.model.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AutorRequest {
    private Integer idPersona;
    private String biografia;
    private LocalDate fechaFallecimiento;
    private String fotoUrl;
}