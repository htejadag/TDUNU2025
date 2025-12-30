package com.unu.ms.MsGradosTitulos.model.response;

import com.unu.ms.MsGradosTitulos.model.entity.ResolucionModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionArticuloResponse {
    
    public Integer idArticulo;
    public ResolucionModel resolucion;
    public Integer numeroArticulo;
    public String titulo;
    public String contenido;
    
}
