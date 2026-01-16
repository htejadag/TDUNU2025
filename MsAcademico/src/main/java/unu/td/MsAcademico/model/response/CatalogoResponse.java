package unu.td.MsAcademico.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatalogoResponse implements Serializable {
    private Integer id;
    private String categoria;
    private Integer codigo;
    private String nombre;
    private String abreviatura;
    private Integer valor;
    private Integer orden;
}