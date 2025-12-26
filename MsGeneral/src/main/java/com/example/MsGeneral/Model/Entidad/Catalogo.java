package com.example.MsGeneral.Model.Entidad;

import lombok.*;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection  = "Catalogo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Catalogo {

    @Id
    private String idCatalogo;

    @Field("CATEGORIA")
    private String categoria;

    @Field("CODIGO")
    private Integer codigo;

    @Field("DESCRIPCION")
    private String descripcion;

    @Field("ABREVIATURA")
    private String abreviatura;

    @Field("ORDEN")    
    private Integer orden;

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
