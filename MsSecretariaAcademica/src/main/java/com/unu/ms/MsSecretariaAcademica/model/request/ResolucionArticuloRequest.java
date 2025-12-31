package com.unu.ms.MsSecretariaAcademica.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionArticuloRequest {

    //Integer idArticulo;
    Integer numeroArticulo;
    String titulo;
    String contenido;
    Integer resolucion;

}
