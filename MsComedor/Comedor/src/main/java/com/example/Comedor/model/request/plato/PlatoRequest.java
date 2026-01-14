package com.example.Comedor.model.request.plato;

import lombok.Data;

@Data
public class PlatoRequest {

    private String nombre;
  
    private String descripcion;
 
    private String imagenUrl;

    private double calorias;
  
    private Integer idTipo;

    private boolean activo;

    private Integer usuarioCreacion;

 

}
