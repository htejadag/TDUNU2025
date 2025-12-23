package com.example.Comedor.model.request.menuSemana;


import lombok.Data;

@Data
public class MenuSemanaRequest {

    private String fechaInicio;
  
    private String fechaFin;

    private boolean activo;

    private Integer usuarioCreacion;


    
    
}
