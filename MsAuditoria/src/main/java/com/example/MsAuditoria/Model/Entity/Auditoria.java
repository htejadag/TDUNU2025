package com.example.MsAuditoria.Model.Entity;

import lombok.*;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Auditoria")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {

    @Id
    private String idAuditoria;

    @Field("MICROSERVICIO")
    private String microservicio;

    @Field("MODULO")
    private String modulo;

    @Field("OPERACION")
    private String operacion;

    @Field("ENTIDAD")
    private String entidad;

    @Field("ID_ENTIDAD")
    private String idEntidad;

    @Field("DATOS")
    private Object datos;

    @Builder.Default
    @Field("USUARIO")
    private String usuario = "System";

    @CreatedDate
    @Field("FECHA_EVENTO")
    private LocalDateTime fechaEvento;
    
}
