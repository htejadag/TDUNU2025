package com.MsExamen.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CatalogoDto {
    private Integer idCatalogo;
    private String nombre;
    private String descripcion;
    private Boolean estado;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;
}
