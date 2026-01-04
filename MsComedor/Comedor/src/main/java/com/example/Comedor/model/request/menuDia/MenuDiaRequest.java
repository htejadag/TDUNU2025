package com.example.Comedor.model.request.menuDia;



import lombok.Data;

@Data
public class MenuDiaRequest {

    private Integer idMenuSemana;
    
    private Integer dia;

    private Integer racionesTotales;

    private boolean activo;

    private Integer usuarioCreacion;

    
    
}
