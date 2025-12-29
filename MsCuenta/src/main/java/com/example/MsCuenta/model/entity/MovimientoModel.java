package com.example.MsCuenta.model.entity;


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

@Data
@Entity
@Table(name = "movimiento")
public class MovimientoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_cuenta_usuario", referencedColumnName = "id")
    private CuentaUsuarioModel idCuentaUsuario;

    @ManyToOne
    @JoinColumn(name = "id_tipo_movimiento", referencedColumnName = "id")
    private CatalogoModel idTipoMovimiento;

    @Column (name = "monto")
    private Integer monto;
    @Column (name = "fecha_movimiento")
    private LocalDate fechaMovimiento;
    @Column (name = "activo")
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
