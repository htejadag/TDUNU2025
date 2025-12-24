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

/**
 * Documento Catalogo (MongoDB)
 * Colección: catalogo
 * Almacena valores de catálogo agrupados por tipo
 */
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

    /**
     * Grupo del catálogo (ej: ESTADO_PROYECTO, ROL_JURADO, etc.)
     */
    @Field("grupo")
    private String grupo;

    /**
     * Código único dentro del grupo
     */
    @Field("codigo")
    private String codigo;

    /**
     * Nombre descriptivo del ítem
     */
    @Field("nombre")
    private String nombre;

    /**
     * Indica si el ítem está activo
     */
    @Field("activo")
    @Builder.Default
    private Boolean activo = true;

    /**
     * Orden de presentación dentro del grupo
     */
    @Field("orden")
    private Integer orden;

    /**
     * Fecha de creación del registro
     */
    @CreatedDate
    @Field("createdAt")
    private LocalDateTime createdAt;

    /**
     * Fecha de última actualización
     */
    @LastModifiedDate
    @Indexed(name = "idx_updatedAt_desc", direction = org.springframework.data.mongodb.core.index.IndexDirection.DESCENDING)
    @Field("updatedAt")
    private LocalDateTime updatedAt;
}
