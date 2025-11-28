package com.unu.ms.MsConsejo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsejoRequest {
    
    private String nombre;
    private String descripcion;
    private Integer estado;
    
    // private List<MiembroConsejoModel> miembros;
    // private List<SesionConsejoModel> sesiones;
}
