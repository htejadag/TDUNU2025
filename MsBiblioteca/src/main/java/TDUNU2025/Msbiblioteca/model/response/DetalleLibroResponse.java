package TDUNU2025.Msbiblioteca.model.response;

import lombok.Data;

@Data
public class DetalleLibroResponse {

    private Long id;
    private Long idLibro;
    private Integer stockTotal;
    private Integer stockDisponible;
    private String ubicacionFisica;
}
