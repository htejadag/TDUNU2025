package TDUNU2025.Msbiblioteca.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultaResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idMulta;
    private Integer idUsuario;
    private Integer idPrestamo;
    private Double monto;
    private String concepto;
    private LocalDate fechaGeneracion;
    private LocalDate fechaPago;
    private Integer idEstadoMulta;
    private Integer diasRetraso;
}