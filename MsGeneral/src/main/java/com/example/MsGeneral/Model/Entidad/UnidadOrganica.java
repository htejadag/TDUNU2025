package com.example.MsGeneral.Model.Entidad;

import lombok.*;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection  = "UnidadOrganica")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadOrganica {

    @Id
    private String id;

    private String nombre;

    private String siglas;

    @CreatedDate
    private LocalDateTime fechaCreacion;

    @Builder.Default
    private String usuarioCreacion="System";

    @LastModifiedDate
    private LocalDateTime fechaModificacion;

    @Builder.Default
    private String usuarioModificacion="System";

}
