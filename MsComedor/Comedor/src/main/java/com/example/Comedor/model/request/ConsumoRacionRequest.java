package com.example.Comedor.model.request;

import java.sql.Date;

import lombok.Data;

@Data
public class ConsumoRacionRequest {
    private Integer idCuentaUsuario;   
    private Integer idMenuDia;        
    private Date fechaConsumo;

    private Integer usuarioCreacion;

    private Date fechaCreacion;

    private Integer usuarioModificacion;
    
    private Date fechaModificacion;
}
