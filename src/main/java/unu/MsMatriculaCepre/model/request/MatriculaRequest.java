package unu.MsMatriculaCepre.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatriculaRequest {

    @NotNull(message = "El estudiante_id es obligatorio")
    private Integer estudianteId;

    @NotNull(message = "El grupo_id es obligatorio")
    private Integer grupoId;

    private String observaciones;

    private String sistemaOrigen;

    private String usuarioRegistro;
}
