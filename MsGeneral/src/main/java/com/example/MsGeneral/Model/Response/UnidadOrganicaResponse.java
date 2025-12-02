package com.example.MsGeneral.Model.Response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class UnidadOrganicaResponse {

    private Short idUnidad;
    private String nombre;
    private String siglas;

    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;

    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;

}
