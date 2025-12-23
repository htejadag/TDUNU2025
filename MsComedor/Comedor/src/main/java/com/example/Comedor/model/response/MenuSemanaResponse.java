package com.example.Comedor.model.response;


import java.time.LocalDate;

import lombok.Data;

@Data
public class MenuSemanaResponse {

    private Integer id;
    
    private LocalDate fechaInicio;
  
    private LocalDate fechaFin;

    private boolean activo;

    private Integer usuarioCreacion;

    private LocalDate fechaCreacion;

    private Integer usuarioModificacion;
    
    private LocalDate fechaModificacion;

    
}
