package com.unu.TDUNU2025.Msbiblioteca.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBase<T> {
    
    private String codigo;
    private String mensaje;
    private T data;
    
    // Constructor auxiliar para respuestas exitosas rápidas
    public ResponseBase(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }
}