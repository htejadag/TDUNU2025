package com.unu.ms.MsConsejo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoRequest {

    // Integer idCatalogo;
    Integer idPadre;
    String descripcionCatalogo;
    String abreviaturaCatalogo;
    String estadoCatalogo;

}
