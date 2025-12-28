package tdunu.MsTitulacion.model.response;

import lombok.Data;

@Data
public class ActaAprobacionResponse {
    private String codigoUnicoActa;  // Ej: "2023-151" [cite: 533]
    private String fechaEmisionFormateada; // "28 de ABRIL del 2023" [cite: 535]
    private String rutaDescargaPdf;  // URL para el botón "Descargar Acta" [cite: 528]
    private String escuelaProfesional; // "INGENIERIA DE SISTEMAS" [cite: 541]
}