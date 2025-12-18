package com.unu.ms.MsConsejo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoResponse {

    Integer idCatalogo;
    Integer idPadre;
    String descripcionCatalogo;
    String abreviaturaCatalogo;
    String estadoCatalogo;

}
