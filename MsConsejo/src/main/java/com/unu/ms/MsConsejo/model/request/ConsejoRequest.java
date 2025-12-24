package com.unu.ms.MsConsejo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsejoRequest {

    // private Integer idConsejo;
    private String nombre;
    private String descripcion;
    private Integer idEstado;
    // private LocalDateTime fechaCreacion;
    // private List<MiembroConsejoModel> miembros;
    // private List<SesionConsejoModel> sesiones;

}
