package com.MsExamen.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ExamenRequest {
    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    private String descripcion;
    private Integer idTipoExamen;

    private String usuarioCreacion;
    private String usuarioModificacion;
    private java.time.LocalDateTime fechaCreacion;
    private java.time.LocalDateTime fechaModificacion;
}
