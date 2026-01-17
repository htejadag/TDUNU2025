package com.example.MsPlanEstudios.model.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class cursoDetalleEvent {
    private Integer idDetalleCurso;
    private String cursoNombre;
}
