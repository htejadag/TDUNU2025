package com.example.MsEvaluacion.model.response;

import java.io.Serializable; 
import lombok.Data;

@Data
public class CatalogoResponse implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String codigo; 
    private String nombre; 
}