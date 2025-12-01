package com.unu.MsDocumentos.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentoRequest {

    private String correlativo;
    private LocalDateTime fechaEmision;
    private UUID expedienteId;
    private Integer tipoDocumentoId;
    private UUID oficinaOrigenId;
    private String asunto;
    private String referencia;
}