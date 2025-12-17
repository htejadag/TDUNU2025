package com.unu.TDUNU2025.Msbiblioteca.model.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MultaResponse {
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