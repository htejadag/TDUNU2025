package com.unu.ms.MsConsejo.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsejoResponse{


    Integer idConsejo;
    String nombre;
    String descripcion;
    Integer idEstado;
    LocalDateTime fechaCreacion;
    // List<MiembroConsejoModel> miembros;
    // List<SesionConsejoModel> sesiones;

}