package unu.td.MsAcademico.model.response;

import lombok.Data;

@Data
public class CatalogoResponse {
    private Integer id;
    private String categoria;
    private Integer codigo;
    private String nombre;
    private String abreviatura;
    private Integer valor;
    private Integer orden;
}