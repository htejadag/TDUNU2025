package com.example.Comedor.model.response;

import lombok.Data;

@Data
public class ComedorResponse {

    private Integer id;
    
    private String nombre;
  
    private String descripcion;
 
    private String imagenUrl;

    private double calorias;
  
    private String tipo;

    private boolean activo;

    
}
