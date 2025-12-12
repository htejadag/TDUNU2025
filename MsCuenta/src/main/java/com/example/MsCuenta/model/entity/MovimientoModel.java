package com.example.MsCuenta.model.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "movimiento")
public class MovimientoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_cuenta_usuario")
    private Integer id_cuenta_usuario;

    @Column(name = "id_tipo_movimiento")
    private Integer id_tipo_movimiento;
    @Column (name = "id_operacion")
    private Integer id_operacion;
    @Column (name = "monto")
    private Integer monto;
    @Column (name = "fecha_movimiento")
    private Date fecha_movimiento;
    
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
