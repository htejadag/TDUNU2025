package com.example.Comedor.model.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MenuDiaResponse {

    private Integer id;

    private Integer idMenuSemana; 

    private Integer dia;

    private boolean activo;

    private Integer usuarioCreacion;

    private LocalDate fechaCreacion;

    private Integer usuarioModificacion;
    
    private LocalDate fechaModificacion;
    
}
