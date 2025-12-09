package com.example.Comedor.model.request;

import java.sql.Date;

import lombok.Data;

@Data
public class MenuDiaRequest {

    private Integer idMenuSemana;   
    private Integer idTurno;        
    private Integer racionesTotales;
    private Integer racionesRestantes;
    private boolean activo;

    private Integer usuarioCreacion;

    private Date fechaCreacion;

    private Integer usuarioModificacion;
    
    private Date fechaModificacion;
    
}
