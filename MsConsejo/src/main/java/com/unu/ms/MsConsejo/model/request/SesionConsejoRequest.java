package com.unu.ms.MsConsejo.model.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesionConsejoRequest {

    // private Integer idSesion;
    // private ConsejoModel idConsejo;
    private String numeroSesion;
    private String nombreSesion;
    private LocalDate fechaSesion;
    private Integer idTipoSesion;
    private String descripcion;
    // private Integer idEstado;
    // private Integer usuarioRegistro;
    // private LocalDateTime fechaCreacion;
    // private List<AsistenciaSesionModel> asistencias;

}
