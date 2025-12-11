package com.unu.ms.MsConsejo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsejoResponse {

    public Integer idConsejo;
    public String nombre;
    public String descripcion;
    public Integer idEstado;
    // public LocalDateTime fechaCreacion;
    // public List<MiembroConsejoModel> miembros;
    // public List<SesionConsejoModel> sesiones;
    
}