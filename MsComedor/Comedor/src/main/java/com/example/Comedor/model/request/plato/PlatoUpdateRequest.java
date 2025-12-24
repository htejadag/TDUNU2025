package com.example.Comedor.model.request.plato;

import lombok.Data;

@Data
public class PlatoUpdateRequest {

    private String nombre;
  
    private String descripcion;
 
    private String imagenUrl;

    private Integer calorias;
  
    private String tipo;

    private boolean activo;

    private Integer usuarioModificacion;

    
}
