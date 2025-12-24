package com.example.Comedor.model.response;


import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
 
@Data
public class TurnoResponse {

    private Integer id;
    private String descripcion;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private boolean activo;

    private Integer usuarioCreacion;

    private LocalDate fechaCreacion;

    private Integer usuarioModificacion;
    
    private LocalDate fechaModificacion;
    
}
