package com.pago.model.request;

import java.sql.Date;
import lombok.Data;

@Data
public class PagoRequest {

  private String serie;

  private String correlativo;

  private Date fechaPago;

  private Double montoTotal;

  private String estado;

  private Double descuento;

  private String observaciones;

  private Integer tipoPagoId;

  private Integer personaId;

  private Integer usuarioId;
}
