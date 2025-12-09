package com.example.MsGeneral.Model.Response;



import java.time.LocalDate;
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

    private String id;
    private String nombre;
    private String siglas;

}
