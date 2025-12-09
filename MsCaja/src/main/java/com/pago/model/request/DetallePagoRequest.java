package com.pago.model.request;


import lombok.Data;

@Data
public class DetallePagoRequest {

  public String codigo_recibo;

  public Integer cantidad;

  public Double precio_unitario;

  public Double subtotal;

  public Double descuento;

  public Integer conceptoPagoId;

  public Integer pagoId;
}