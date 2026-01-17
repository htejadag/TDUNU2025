package com.unu.MsDocumentos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "documentos")
public class Documento extends AuditoriaEstado {

    @Id
    private String id;

    private String correlativo;

    private LocalDateTime fechaEmision;

    @Field("expediente_id")
    private String expedienteId;

    @Field("tipo_documento")
    private String tipoDocumento;

    @Field("oficina_origen_id")
    private String oficinaOrigen;

    private String asunto;

    private String referencia;

    private List<Archivo> archivos = new ArrayList<>();
}