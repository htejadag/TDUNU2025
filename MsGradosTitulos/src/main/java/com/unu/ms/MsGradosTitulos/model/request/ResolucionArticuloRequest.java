package com.unu.ms.MsGradosTitulos.model.request;

import com.unu.ms.MsGradosTitulos.model.entity.ResolucionModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionArticuloRequest {
    
    // public Integer idArticulo;
    public ResolucionModel resolucion;
    public Integer numeroArticulo;
    public String titulo;
    public String contenido;
    
}
