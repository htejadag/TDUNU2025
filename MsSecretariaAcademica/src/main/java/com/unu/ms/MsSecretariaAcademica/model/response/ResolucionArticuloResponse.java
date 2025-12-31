package com.unu.ms.MsSecretariaAcademica.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionArticuloResponse {

    Integer idArticulo;
    Integer numeroArticulo;
    String titulo;
    String contenido;
    Integer resolucion;

}
