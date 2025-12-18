package com.unu.ms.MsConsejo.model.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsejoDetalleCompletoResponse {

    private Integer idConsejo;
    private String nombre;
    private String descripcion;
    private Integer idEstado;
    private LocalDateTime fechaCreacion;
    private Integer totalMiembrosActivos;
    private Integer totalSesionesRealizadas;
    private List<MiembroConsejoResponse> miembrosActivos;

}
