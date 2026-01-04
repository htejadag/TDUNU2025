package com.example.Comedor.model.request.menuDia;


import lombok.Data;

@Data
public class MenuDiaUpdateRequest {

    private Integer idMenuSemana;   

    private Integer Dia;

    private Integer racionesTotales;

    private Integer racionesRestantes;

    private boolean activo;

    private Integer usuarioModificacion;
    
  
    
}
