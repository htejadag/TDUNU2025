package com.example.Comedor.model.request.menuDia;


import lombok.Data;

@Data
public class MenuDiaUpdateRequest {

    private Integer idMenuSemana;   

    private Integer Dia;

    private boolean activo;

    private Integer usuarioModificacion;
    
  
    
}
