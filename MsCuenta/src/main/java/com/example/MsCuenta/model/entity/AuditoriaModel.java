package com.example.MsCuenta.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
// @Entity
@MappedSuperclass
// @Table(name = "auditoria_log")

public class AuditoriaModel {
    
    // HE COMENTADO ESTO PARA QUE NO HAYA CONFLICTO CON EL ID DE 'CuentaUsuarioModel'
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Integer id;

    private String entidad;       // Ej: "CuentaUsuario"
    private Integer idRegistro;   // El ID de la cuenta afectada
    private String accion;        // "CREAR", "ACTUALIZAR", "ELIMINAR"
    private Integer idUsuario;    // Quién hizo la acción
    private LocalDateTime fecha;  // Cuándo ocurrió
    
    @Column(columnDefinition = "TEXT")
    private String detalles;      // JSON o texto con lo que cambió
}