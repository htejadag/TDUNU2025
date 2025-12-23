package com.example.Comedor.model.request.menuSemana;



import lombok.Data;

@Data
public class MenuSemanaUpdateRequest {

    private String fechaInicio;
  
    private String fechaFin;

    private boolean activo;

    private Integer usuarioModificacion;



    
}
