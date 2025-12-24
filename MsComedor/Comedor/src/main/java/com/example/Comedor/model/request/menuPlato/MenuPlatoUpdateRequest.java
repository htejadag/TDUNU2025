package com.example.Comedor.model.request.menuPlato;

import lombok.Data;

@Data
public class MenuPlatoUpdateRequest {

    private Integer idMenuDia;

    private Integer idTurno;

    private Integer idPlato;

    private boolean activo;

    private Integer usuarioModificacion;
    
}
