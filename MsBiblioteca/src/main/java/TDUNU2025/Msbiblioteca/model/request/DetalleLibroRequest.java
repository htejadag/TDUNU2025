package TDUNU2025.Msbiblioteca.model.request;

import lombok.Data;

@Data
public class DetalleLibroRequest {

    private Long idLibro;          // FK al libro
    private Integer stockTotal;
    private Integer stockDisponible;
    private String ubicacionFisica;
}
