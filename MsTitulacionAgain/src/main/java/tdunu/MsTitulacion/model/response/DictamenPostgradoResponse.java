package tdunu.MsTitulacion.model.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DictamenPostgradoResponse {
    private Integer idActo;
    private Integer idBorrador;
    
    private LocalDate fechaProgramada;
    private LocalTime horaProgramada;
    private String aulaVirtualFisica;

    // Se devuelven los IDs por si se necesitan para editar
    private Integer idModalidadCat;
    private Integer idResultadoFinalCat;

    // Se devuelven los TEXTOS (Valores) para mostrar en la grilla/tabla
    private String nombreModalidad;      // Ej: "Virtual"
    private String nombreResultadoFinal; // Ej: "Aprobado por Unanimidad"
}