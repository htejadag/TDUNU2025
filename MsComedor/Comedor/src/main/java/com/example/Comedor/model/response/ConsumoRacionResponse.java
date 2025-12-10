package com.example.Comedor.model.response;

import java.sql.Date;

import lombok.Data;

@Data
public class ConsumoRacionResponse {
    private Integer id;
    private Integer idCuentaUsuario;   
    private Integer idMenuDia;        
    private Date fechaConsumo;

    private Integer usuarioCreacion;

    private Date fechaCreacion;

    private Integer usuarioModificacion;
    
    private Date fechaModificacion;
}
