package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "catalogo")
@Data
@EqualsAndHashCode(callSuper = true)
public class CatalogoModel extends AuditoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_catalogo")
    private Integer idCatalogo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_padre")
    private CatalogoModel padre;

    @OneToMany(mappedBy = "padre", fetch = FetchType.LAZY)
    private List<CatalogoModel> hijos;

    @Column(name = "descripcion_catalogo", length = 100)
    private String descripcionCatalogo;

    @Column(name = "abreviatura_catalogo", length = 10)
    private String abreviaturaCatalogo;

    @Column(name = "estado_catalogo", length = 10)
    private String estadoCatalogo;
}
