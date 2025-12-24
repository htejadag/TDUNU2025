package com.example.Comedor.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "menu_plato")
@Data
public class MenuPlatoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_menu_dia", nullable = false)
    private MenuDiaModel menuDia;

    @ManyToOne
    @JoinColumn(name = "id_turno", nullable = false)
    private TurnoModel turno;

    @ManyToOne
    @JoinColumn(name = "id_plato", nullable = false)
    private PlatoModel plato;

     @Column(name = "activo",nullable = false)
    private boolean activo;

    //auditoria
    @Column(name = "usuario_creacion", nullable = false, length = 100)
    private Integer usuarioCreacion;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;
    @Column(name = "usuario_modificacion", length = 100)
    private Integer usuarioModificacion;
    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;
    
}
