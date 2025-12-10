package com.unu.ms.MsConsejo.model.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesionConsejoResponse {
    
    // public Integer idSesion;
    // public ConsejoModel consejo;
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
