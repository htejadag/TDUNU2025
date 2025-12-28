package tdunu.MsTitulacion.model.request;

import lombok.Data;

@Data
public class ActaAprobacionRequest {

    // ID del proyecto de tesis asociado [cite: 870]
    private Integer idProyecto;

    // Código único según el manual (Ej: "2023-151") [cite: 533]
    private String codigoUnicoActa;

    // URL o ruta donde se almacenó el PDF generado [cite: 528]
    private String rutaPdfFirmado;
}