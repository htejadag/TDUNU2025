package com.example.Comedor.model.response;

import java.sql.Date;

import lombok.Data;

@Data
public class MenuPlatoResponse {

    private Integer id;
    private Integer idMenuDia;
    private Integer idPlato;

    private Integer usuarioCreacion;

    private Date fechaCreacion;

    private Integer usuarioModificacion;
    
    private Date fechaModificacion;
    
}
