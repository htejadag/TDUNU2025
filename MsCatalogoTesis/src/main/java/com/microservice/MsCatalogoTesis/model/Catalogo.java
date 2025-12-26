package com.microservice.MsCatalogoTesis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "catalogo")
@CompoundIndex(name = "uq_grupo_codigo", def = "{'grupo': 1, 'codigo': 1}", unique = true)
@CompoundIndex(name = "idx_grupo_activo_orden", def = "{'grupo': 1, 'activo': 1, 'orden': 1}")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Catalogo {

    @Id
    private String id;

    @Field("grupo")
    private String grupo;

    @Field("codigo")
    private String codigo;

    @Field("nombre")
    private String nombre;

    @Field("activo")
    @Builder.Default
    private Boolean activo = true;

    @Field("orden")
    private Integer orden;

    @CreatedDate
    @Field("createdAt")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Indexed(name = "idx_updatedAt_desc", direction = org.springframework.data.mongodb.core.index.IndexDirection.DESCENDING)
    @Field("updatedAt")
    private LocalDateTime updatedAt;
}
