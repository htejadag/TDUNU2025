package tdunu.MsTitulacion.model.request;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DictamenPostgradoRequest {
    private Integer idBorrador;
    private LocalDate fechaProgramada;
    private LocalTime horaProgramada;
    private String aulaVirtualFisica;
    private Integer idModalidadCat;      // Se envía el ID del catálogo (Combo box)
    private Integer idResultadoFinalCat; // Se envía el ID del catálogo
}