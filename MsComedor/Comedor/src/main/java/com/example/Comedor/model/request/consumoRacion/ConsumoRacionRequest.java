package com.example.Comedor.model.request.consumoRacion;




import lombok.Data;

@Data
public class ConsumoRacionRequest {

    private Integer idCuentaUsuario; 

    private Integer idMenuPlato;  
    
    private boolean activo;

    private Integer usuarioCreacion;


}
