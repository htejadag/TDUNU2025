package com.unu.ms.MsConsejo.model.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesionConsejoRequest {
    
    // public Integer idSesion;
    // public ConsejoModel idConsejo;
    public String numeroSesion;
    public String nombreSesion;
    public LocalDate fechaSesion;
    public Integer idTipoSesion;
    public String descripcion;
    // public Integer idEstado;
    // public Integer usuarioRegistro;
    // public Timestamp fechaCreacion;
    // public List<AsistenciaSesionModel> asistencias;

}
