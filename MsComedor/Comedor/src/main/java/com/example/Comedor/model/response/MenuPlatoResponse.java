package com.example.Comedor.model.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MenuPlatoResponse {

    private Integer id;

    private Integer idMenuDia;

    private Integer idPlato;

    private Integer idTurno;

    private Integer usuarioCreacion;

    private LocalDate fechaCreacion;

    private Integer usuarioModificacion;
    
    private LocalDate fechaModificacion;
    
}
