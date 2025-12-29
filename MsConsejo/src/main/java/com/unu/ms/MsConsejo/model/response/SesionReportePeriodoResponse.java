package com.unu.ms.MsConsejo.model.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SesionReportePeriodoResponse {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long totalSesiones;
    private Long sesionesRealizadas;
    private Long sesionesPendientes;
    private Long sesionesCanceladas;

}
