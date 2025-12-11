package com.example.MsCuenta.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class AuditoriaModel {
    
    // VARIABLES NUEVAS (Las que tu servicio sí llena)
    private String entidad;       
    private Integer idRegistro;   
    private String accion;        
    private Integer idUsuario;    
    private LocalDateTime fecha;
    
    @Column(columnDefinition = "TEXT")
    private String detalles;      

}