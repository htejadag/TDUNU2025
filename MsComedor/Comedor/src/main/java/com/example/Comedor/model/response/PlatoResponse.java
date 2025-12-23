package com.example.Comedor.model.response;

import java.sql.Date;

import lombok.Data;

@Data
public class PlatoResponse {

    private Integer id;
    
    private String nombre;
  
    private String descripcion;
 
    private String imagenUrl;

    private Integer calorias;
  
    private String tipo;

    private boolean activo;
    

    private Integer usuarioCreacion;

    private Date fechaCreacion;

    private Integer usuarioModificacion;
    
    private Date fechaModificacion;
    
}
