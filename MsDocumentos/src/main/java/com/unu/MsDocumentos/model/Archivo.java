package com.unu.MsDocumentos.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "archivos")
public class Archivo extends AuditoriaEstado {

    private Integer version;
    private String nombreOriginal;
    private String rutaAcceso;
    private String mimeType;
    private Long peso;
    private String tipoArchivo;
}