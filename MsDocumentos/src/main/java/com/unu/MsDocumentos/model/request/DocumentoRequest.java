package com.unu.MsDocumentos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentoRequest {

    private String correlativo;
    private LocalDateTime fechaEmision;
    private String expedienteId;
    private String tipoDocumento;
    private String oficinaOrigen;
    private String asunto;
    private String referencia;

    private List<ArchivoRequest> archivos;
}