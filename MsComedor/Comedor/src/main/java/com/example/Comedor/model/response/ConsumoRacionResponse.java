package com.example.Comedor.model.response;


import java.time.LocalDate;

import lombok.Data;

@Data
public class ConsumoRacionResponse {
    private Integer id;

    private Integer idCuentaUsuario;

    private Integer idMenuPlato;  

    private LocalDate fechaConsumo;

    private Integer usuarioCreacion;

    private LocalDate fechaCreacion;

    private Integer usuarioModificacion;
    
    private LocalDate fechaModificacion;
}
