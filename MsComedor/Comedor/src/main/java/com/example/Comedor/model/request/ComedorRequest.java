package com.example.Comedor.model.request;

import lombok.Data;

@Data
public class ComedorRequest {

    private String nombre;
  
    private String descripcion;
 
    private String imagenUrl;

    private double calorias;
  
    private String tipo;

    private boolean activo;

    
}
