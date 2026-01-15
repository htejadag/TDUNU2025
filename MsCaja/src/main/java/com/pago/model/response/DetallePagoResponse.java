package com.pago.model.response;

import lombok.Data;

@Data
public class DetallePagoResponse {

  private String codigoRecibo;

  private Integer cantidad;

  private Double precioUnitario;

  private Double subtotal;

  private Double descuento;

  private Integer conceptoPagoId;

  private Integer pagoId;
}
