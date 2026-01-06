package com.example.MsGeneral.Kafka.Event;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaEvent {

    private String microservicio;   // MsGeneral
    private String modulo;          // Catalogo | UnidadOrganica
    private String operacion;       // CREATE | UPDATE | DELETE
    private String entidad;         // Catalogo | UnidadOrganica
    private String idEntidad;       // id Mongo
    private Object datos;           // snapshot
    private String usuario;
    private LocalDateTime fechaEvento;
}
