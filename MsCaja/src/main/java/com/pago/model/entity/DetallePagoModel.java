package com.pago.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_pago")
public class DetallePagoModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "detallepagoid")
  private Integer detallepagoid;

  @Column(name = "codigo_recibo")
  private String codigo_recibo;

  @Column(name = "cantidad")
  private Integer cantidad;

  @Column(name = "precio_unitario")
  private Double precio_unitario;

  @Column(name = "subtotal")
  private Double subtotal;

  @Column(name = "descuento")
  private Double descuento;

  @Column(name = "conceptopagoid")
  private Integer conceptoPagoId;

  @Column(name = "pagoid")
  private Integer pagoId;

}
