package com.pago.model.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "concepto_pago")
@Data
public class ConceptoPagoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conceptopagoid")
<<<<<<< Updated upstream
    private Integer conceptopagoid;
    
    @Column(name = "clasificadoringresoid")
    private Integer clasificadoringresoid;
=======
    private Integer conceptoPagoId;

    @ManyToOne
    @JoinColumn(name = "clasificadorid", foreignKey = @ForeignKey(name = "FK_concepto_pago_clasificador_ingreso"))
    private ClasificadorIngresoModel clasificadorIngreso;
>>>>>>> Stashed changes

    @Column(name = "nombre_concepto")
    private String nombreConcepto;

    @Column(name = "precio_base")
    private float precioBase;

    @Column(name = "usuario_creacion")
    private Integer usuarioCreacion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_modificacion", nullable = true)
    private Integer usuarioModificacion;

    @Column(name = "fecha_modificacion", nullable = true)
    private LocalDateTime fechaModificacion;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "es_eliminado")
    private Boolean esEliminado;

    @Transient
    private String mensaje;

}
