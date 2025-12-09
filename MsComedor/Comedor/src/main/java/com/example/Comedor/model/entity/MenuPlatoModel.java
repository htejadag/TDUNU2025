package com.example.Comedor.model.entity;

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
    @JoinColumn(name = "id_plato", nullable = false)
    private PlatoModel plato;
    
}
