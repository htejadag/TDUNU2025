package tdunu2025.msbiblioteca.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EditorialResponse {
    private Long idEditorial;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String sitioWeb;
    private String pais;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;
}