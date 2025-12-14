package com.MsExamen.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CatalogoDetalleDto {
    private Integer idCatalogoDetalle;
    private Integer idCatalogo;
    private String nombre;
    private String valor;
    private Boolean estado;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;
}
