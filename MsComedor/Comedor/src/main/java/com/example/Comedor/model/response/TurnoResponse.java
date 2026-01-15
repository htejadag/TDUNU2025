package com.example.Comedor.model.response;


import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
 
@Data
public class TurnoResponse {

    private Integer id;

    private Integer idTipoTurno;

    private LocalTime horaApertura;

    private LocalTime horaCierre;

    private Integer racionesTotales;

    private Integer racionesRestantes;

    private boolean activo;

    private Integer usuarioCreacion;

    private LocalDate fechaCreacion;

    private Integer usuarioModificacion;
    
    private LocalDate fechaModificacion;
    
}
