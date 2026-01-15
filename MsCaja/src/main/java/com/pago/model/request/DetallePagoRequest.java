package com.pago.model.request;

import lombok.Data;

@Data
public class DetallePagoRequest {

  private String codigoRecibo;

  private Integer cantidad;

  private Double precioUnitario;

  private Double subtotal;

  private Double descuento;

  private Integer conceptoPagoId;

  private Integer pagoId;
}
