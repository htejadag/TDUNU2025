package com.example.MsGeneral.Model.Entidad;

import lombok.*;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection  = "UnidadOrganica")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadOrganica {

    @Id
    private String id;

    @Field("NOMBRE")
    private String nombre;

    @Field("SIGLAS")
    private String siglas;

    @Field("ACTIVO")
    private boolean activo;

    @CreatedDate
    @Field("FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @Builder.Default
    @Field("USUARIO_CREACION")
    private String usuarioCreacion="System";

    @LastModifiedDate
    @Field("FECHA_MODIFICACION")
    private LocalDateTime fechaModificacion;

    @Builder.Default
    @Field("USUARIO_MODIFICACION")
    private String usuarioModificacion="System";

}
