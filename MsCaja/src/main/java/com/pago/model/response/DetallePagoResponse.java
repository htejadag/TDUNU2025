package com.pago.model.response;


import lombok.Data;

@Data
public class DetallePagoResponse {

  public String codigo_recibo;

  public Integer cantidad;

  public Double precio_unitario;

  public Double subtotal;

  public Double descuento;

  public Integer conceptoPagoId;

  public Integer pagoId;
}