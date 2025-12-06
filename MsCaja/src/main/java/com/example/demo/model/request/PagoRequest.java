package com.example.demo.model.request;

import java.sql.Date;

import lombok.Data;

@Data
public class PagoRequest {

  public String serie;

  public String correlativo;

  public Date fecha_pago;

  public Double monto_total;

  public String estado;

  public String descuento;

  public String observaciones;

  public Integer tipopagoid;

  public Integer personaid;

  public Integer usuarioid;
}