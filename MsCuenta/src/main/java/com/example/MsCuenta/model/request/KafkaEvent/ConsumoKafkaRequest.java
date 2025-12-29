package com.example.MsCuenta.model.request.KafkaEvent;

import lombok.Data;

@Data
public class ConsumoKafkaRequest {

    
    private Integer idCuentaUsuario;
    private Integer saldo;

    
}
 