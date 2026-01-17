package com.example.mscursos.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogoRequest {

    @NotBlank
    private String categoria;

    @NotBlank
    private String nombre;

    private Integer orden;

    @NotNull
    private Integer idPadre;

}
