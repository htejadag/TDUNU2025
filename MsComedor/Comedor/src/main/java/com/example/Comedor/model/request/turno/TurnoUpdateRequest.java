package com.example.Comedor.model.request.turno;

import lombok.Data;

@Data
public class TurnoUpdateRequest {

    private Integer idTipoTurno;

    private String horaApertura;

    private String horaCierre;
    
    private Integer racionesTotales;

    private boolean activo;

    private Integer usuarioModificacion;

    
}
