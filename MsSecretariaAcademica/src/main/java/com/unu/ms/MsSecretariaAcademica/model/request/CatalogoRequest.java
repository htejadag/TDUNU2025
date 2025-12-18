package com.unu.ms.MsSecretariaAcademica.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoRequest {

    Integer idCatalogo;
    String categoria;
    String valor;
    String descripcion;

}