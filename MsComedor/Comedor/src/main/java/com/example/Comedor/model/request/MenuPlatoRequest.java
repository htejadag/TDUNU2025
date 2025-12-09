package com.example.Comedor.model.request;

import java.sql.Date;

import lombok.Data;

@Data
public class MenuPlatoRequest {

    private Integer idMenuDia;
    private Integer idPlato;

    private Integer usuarioCreacion;

    private Date fechaCreacion;

    private Integer usuarioModificacion;
    
    private Date fechaModificacion;
    
    
}
