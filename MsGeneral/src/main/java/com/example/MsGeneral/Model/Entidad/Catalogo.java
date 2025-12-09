package com.example.MsGeneral.Model.Entidad;

import lombok.*;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection  = "Catalogo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Catalogo {

    @Id
    private String idCatalogo;

    private String nombreTabla;

    private String codigo;

    private String descripcion;

    private String abreviatura;

    private Integer orden;

    @CreatedDate
    private LocalDateTime fechaCreacion;

    @Builder.Default
    private String usuarioCreacion="System";

    @LastModifiedDate
    private LocalDateTime fechaModificacion;

    @Builder.Default
    private String usuarioModificacion="System";

}
