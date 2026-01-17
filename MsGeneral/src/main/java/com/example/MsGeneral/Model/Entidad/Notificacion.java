package com.example.MsGeneral.Model.Entidad;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection  = "Notificacion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    private String idNotificacion;

    @Field("ID_USUARIO")
    private String idUsuario;

    @Field("MENSAJE")
    private String mensaje;

    @Field("LEIDO")
    private boolean leido;

    @Field("FECHA_CREACION")
    private LocalDateTime fechaCreacion;

}
