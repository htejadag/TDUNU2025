package com.example.MsCuenta.model.request.Catalogo;

import java.sql.Date;

import lombok.Data;

@Data
public class CatalogoRequest {


    private String tipo;

    private boolean activo;
    
    private Integer usuarioCreacion;

    private Date fechaCreacion;

    private Integer usuarioModificacion;

    private Date fechaModificacion;




    


    
}
