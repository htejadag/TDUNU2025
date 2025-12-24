package com.example.Comedor.model.request.consumoRacion;

import lombok.Data;

@Data
public class ConsumoRacionUpdateRequest {

    private Integer idCuentaUsuario; 

    private Integer idMenuPlato;    

    private Integer usuarioModificacion;
    
}
