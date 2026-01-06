package com.example.Comedor.model.request.menuDia;



import lombok.Data;

@Data
public class MenuDiaRequest {

    private Integer idMenuSemana;
    
    private Integer dia;

    private boolean activo;

    private Integer usuarioCreacion;

    
    
}
