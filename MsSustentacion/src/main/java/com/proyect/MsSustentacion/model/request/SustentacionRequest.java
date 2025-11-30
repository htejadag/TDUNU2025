package com.proyect.MsSustentacion.model.request;

import com.fasterxml.jackson.annotation.JsonFormat; // <--- IMPORTANTE
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SustentacionRequest {

    // 1. Usamos @Schema(hidden = true) si es Request, para que no pida ID al crear
    // O le pones un ejemplo si es Response.
    @Schema(hidden = true)
    private Long id;

    @Schema(description = "Número de Acta", example = "ACT-2025-001")
    private String actaNumero;

    @Schema(description = "Lugar de la sustentación", example = "Auditorio Principal")
    private String lugar;

    @Schema(description = "Fecha (YYYY-MM-DD)", example = "2025-11-30", type = "string")
    @JsonFormat(pattern = "yyyy-MM-dd") // <--- Asegura que Java lo lea como String
    private LocalDate fecha;

    // EL CAMBIO CLAVE PARA LA HORA:
    @Schema(description = "Hora (HH:mm:ss)", example = "10:30:00", type = "string")
    @JsonFormat(pattern = "HH:mm:ss") // <--- Esto aplana el objeto a "10:30:00"
    @NotNull
    private LocalTime hora;

    @Schema(description = "Estado (1=Pendiente)", example = "1")
    private Short estadoResulId;

    @Schema(description = "ID del Trámite", example = "1005")
    private Long tramiteId;
}