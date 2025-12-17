package com.unu.TDUNU2025.Msbiblioteca.model.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AutorResponse {
    private Integer idAutor;
    private Integer idPersona;
    private String biografia;
    private LocalDate fechaFallecimiento;
    private String fotoUrl;
}