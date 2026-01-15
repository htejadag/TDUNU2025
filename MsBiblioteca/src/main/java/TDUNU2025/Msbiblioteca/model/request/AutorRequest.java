package tdunu2025.msbiblioteca.model.request;

import lombok.Data;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

@Data
public class AutorRequest {
    
    @NotNull(message = "el Id de persona es obligatorio")
    private Long idPersona;
    private String biografia;
    private LocalDate fechaFallecimiento;
    private String fotoUrl;
}