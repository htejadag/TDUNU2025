package com.example.mscursos.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogoResponse {
    private Integer id;
    private String categoria;
    private String nombre;
    private Integer orden;
    private Integer idPadre;
    private Boolean estado;
}
