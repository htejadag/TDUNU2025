package com.unu.TDUNU2025.Msbiblioteca.model.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PrestamoRequest {
    // No pedimos idPrestamo porque es autogenerado
    private Integer idUsuario;
    private Integer idLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private LocalDate fechaDevolucion; // Puede ser null
    private Integer idEstadoPrestamo;
    private String observaciones;
}