package com.unu.TDUNU2025.Msbiblioteca.model.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DetalleUsuarioResponse {
    private Integer idUsuario;
    private Integer idDetalleUsuario;
    private Integer totalPrestamos;
    private Integer totalMultas;
    private LocalDate fechaUltimoPrestamo;
}