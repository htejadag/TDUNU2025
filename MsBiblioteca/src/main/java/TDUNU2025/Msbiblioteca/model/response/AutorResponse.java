package TDUNU2025.Msbiblioteca.model.response;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorResponse {

    private Integer idAutor;

    private Integer idPersona;

    private String biografia;

    private LocalDate fechaFallecimiento;

    private String fotoUrl;
}