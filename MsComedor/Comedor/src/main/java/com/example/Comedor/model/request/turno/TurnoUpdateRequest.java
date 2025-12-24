package com.example.Comedor.model.request.turno;

import lombok.Data;

@Data
public class TurnoUpdateRequest {

    private String descripcion;

    private String horaApertura;

    private String horaCierre;

    private boolean activo;

    private Integer usuarioModificacion;

    
}
