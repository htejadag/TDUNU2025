package com.unu.ms.MsGradosTitulos.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "resolucion_articulo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionArticuloModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_resolucion")
    private ResolucionModel resolucion;

    @Column(name = "numero_articulo")
    private Integer numeroArticulo;

    private String titulo;

    private String contenido;
}
