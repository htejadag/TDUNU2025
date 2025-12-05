package com.unu.ms.MsConsejo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsejoResponse {
    private Integer idConsejo;
    private String nombre;
    private String descripcion;
    private Integer idEstado;
    private LocalDateTime fechaCreacion;
}
