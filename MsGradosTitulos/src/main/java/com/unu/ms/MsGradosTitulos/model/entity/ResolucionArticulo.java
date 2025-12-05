package com.unu.ms.MsGradosTitulos.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resolucion_articulo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResolucionArticulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo")
    private Integer idArticulo;

    @Column(name = "id_resolucion")
    private Integer idResolucion;

    @Column(name = "numero_articulo")
    private Integer numeroArticulo;

    @Column(name = "titulo", length = 255)
    private String titulo;

    @Column(name = "contenido", columnDefinition = "NVARCHAR(MAX)")
    private String contenido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resolucion", insertable = false, updatable = false)
    private Resolucion resolucion;
}
