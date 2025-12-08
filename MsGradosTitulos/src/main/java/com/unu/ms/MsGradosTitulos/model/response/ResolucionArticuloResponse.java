package com.unu.ms.MsGradosTitulos.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionArticuloResponse {
    private Integer idArticulo;
    private Integer idResolucion;
    private Integer numeroArticulo;
    private String titulo;
    private String contenido;
}
