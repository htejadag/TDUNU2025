package com.service.MsTramiteTesis.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoRequest {

    @NotNull(message = "El ID del asesor es obligatorio")
    private Integer idAsesor;

    @NotNull(message = "El ID de la línea es obligatorio")
    private Integer idLinea;

    @NotBlank(message = "El título del proyecto es obligatorio")
    private String titulo;

    private String rutaArchivoProyecto;

}
