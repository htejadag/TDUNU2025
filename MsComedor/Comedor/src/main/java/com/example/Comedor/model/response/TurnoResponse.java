package com.example.Comedor.model.response;



import lombok.Data;
 
@Data
public class TurnoResponse {

    private Integer id;
    private String descripcion;
    private String horaApertura;
    private String horaCierre;
    private boolean activo;
    
}
