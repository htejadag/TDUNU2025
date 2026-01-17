package unu.td.MsAcademico.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "catalogo")
@EqualsAndHashCode(callSuper=false)
public class CatalogoModel extends BaseModel{

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private Integer codigo;

    @Column(nullable = false)
    private String nombre;

    private String abreviatura;

    private Integer valor;

    private Integer orden;
}