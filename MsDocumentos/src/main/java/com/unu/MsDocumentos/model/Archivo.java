package com.unu.MsDocumentos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Archivo {

    private Integer version;
    private String nombreOriginal;
    private String rutaAcceso;
    private String mimeType;
    private Long peso;
    private String tipoArchivo;
}