package com.example.Comedor.model.entity;


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
@Table(name = "menu_dia")
@Data
public class MenuDiaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_menu_semana", nullable = false)
    private MenuSemanaModel menuSemana;

    @ManyToOne
    @JoinColumn(name = "id_turno", nullable = false)
    private TurnoModel turno;

    @Column(name = "raciones_totales")
    private Integer racionesTotales;

    @Column(name = "raciones_restantes")
    private Integer racionesRestantes;

    private boolean activo;

    //auditoria
    @Column(name = "usuario_creacion", nullable = false, length = 100)
    private Integer usuarioCreacion;
    @Column(name = "fecha_creacion", nullable = false)
    private String fechaCreacion;
    @Column(name = "usuario_modificacion", length = 100)
    private Integer usuarioModificacion;
    @Column(name = "fecha_modificacion")
    private String fechaModificacion;
    
}
