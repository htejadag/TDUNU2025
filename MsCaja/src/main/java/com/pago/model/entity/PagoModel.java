package com.pago.model.entity;

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
@Table(name = "pago")
public class PagoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pagoid")
  private Integer pagoid;

  @Column(name = "serie")
  private String serie;

  @Column(name = "correlativo")
  private String correlativo;

  @Column(name = "fecha_pago")
  private Date fecha_pago;

  @Column(name = "monto_total")
  private Double monto_total;

  @Column(name = "estado")
  private String estado;

  @Column(name = "descuento")
  private String descuento;

  @Column(name = "observaciones")
  private String observaciones;

  @Column(name = "tipopagoid")
  private Integer tipopagoid;

  @Column(name = "personaid")
  private Integer personaid;
  
  @Column(name = "usuarioid")
  private Integer usuarioid;

}
