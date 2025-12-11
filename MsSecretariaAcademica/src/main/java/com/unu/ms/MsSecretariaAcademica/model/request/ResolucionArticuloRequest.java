package com.unu.ms.MsSecretariaAcademica.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionArticuloRequest {
    
    // public Integer idArticulo;
    // public ResolucionModel resolucion;
    public Integer numeroArticulo;
    public String titulo;
    public String contenido;
    
}
