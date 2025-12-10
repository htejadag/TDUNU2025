package com.example.Comedor.model.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "consumo_racion")
public class ConsumoRacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_cuenta_usuario", nullable = false)
    private Integer idCuentaUsuario;

    @ManyToOne
    @JoinColumn(name = "id_menu_dia")
    private MenuDiaModel idMenuDia;

    @Column(name = "fecha_consumo")
    private Date fechaConsumo;

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
