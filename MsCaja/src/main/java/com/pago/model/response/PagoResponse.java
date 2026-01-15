package com.pago.model.response;

import java.sql.Date;
import lombok.Data;

@Data
public class PagoResponse {

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
