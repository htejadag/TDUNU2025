package com.example.Comedor.model.request;

import lombok.Data;

@Data
public class MenuDiaRequest {

    private Integer idMenuSemana;   
    private Integer idTurno;        
    private Integer racionesTotales;
    private Integer racionesRestantes;
    private boolean activo;
    
}
