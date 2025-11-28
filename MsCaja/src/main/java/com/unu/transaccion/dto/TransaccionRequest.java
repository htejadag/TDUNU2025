package com.unu.transaccion.dto;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransaccionRequest {
    private String correlativo;
    private BigDecimal montoTotal;
    private BigDecimal descuento;
    private String observaciones;
    private Integer idCajero;
    private Integer idCliente;
    private Integer idTipoOperacion;
    private Integer idTipoPago;
}