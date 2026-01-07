package com.example.mscursos.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoDetalleResponse {
    private String docenteNombre;
    private String cursoNombre;
    private String semestreNombre;

}
