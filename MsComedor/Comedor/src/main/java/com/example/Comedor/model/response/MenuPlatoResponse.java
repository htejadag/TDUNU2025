package com.example.Comedor.model.response;

import java.time.LocalDate;

import com.example.Comedor.model.entity.MenuDiaModel;
import com.example.Comedor.model.entity.PlatoModel;
import com.example.Comedor.model.entity.TurnoModel;

import lombok.Data;

@Data
public class MenuPlatoResponse {

    private Integer id;

    private MenuDiaModel idMenuDia;

    private TurnoModel idTurno;
   
    private PlatoModel idPlato;

    private Integer usuarioCreacion;

    private LocalDate fechaCreacion;

    private Integer usuarioModificacion;
    
    private LocalDate fechaModificacion;
    
}
