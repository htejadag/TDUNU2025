package tdunu2025.msbiblioteca.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AutorResponse {
    private Long idAutor;
    private Long idPersona;
    private String biografia;
    private LocalDate fechaFallecimiento;
    private String fotoUrl;
}