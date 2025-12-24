package com.unu.TDUNU2025.Msbiblioteca.model.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MultaRequest {
    
    private Integer idUsuario;
    private Integer idPrestamo;
    private Double monto;
    private String concepto;
    private LocalDate fechaGeneracion; // Se envía si se permite fecha manual, sino se puede omitir y usar LocalDate.now() en el Service
    private LocalDate fechaPago;
    private Integer idEstadoMulta;
    
}