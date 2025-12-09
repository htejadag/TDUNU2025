package com.example.Comedor.model.response;

import lombok.Data;

@Data
public class MenuDiaResponse {

    private Integer id;
    private Integer idMenuSemana;   
    private Integer idTurno;        
    private Integer racionesTotales;
    private Integer racionesRestantes;
    private boolean activo;
    
}
