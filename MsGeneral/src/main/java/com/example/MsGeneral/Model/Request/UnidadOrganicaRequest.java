package com.example.MsGeneral.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UnidadOrganicaRequest {
    
    private String nombre;
    private String siglas;
    private boolean activo;
}
