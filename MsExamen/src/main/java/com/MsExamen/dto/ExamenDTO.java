package com.MsExamen.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExamenDTO {
    private Integer idExamen;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;
}
