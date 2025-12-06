package com.MsExamen.service;

import com.MsExamen.dto.PreguntaDTO;
import com.MsExamen.dto.request.PreguntaRequest;
import java.util.List;

public interface IPreguntaService {
    List<PreguntaDTO> getAllPreguntas();

    List<PreguntaDTO> getPreguntasByExamenId(Integer idExamen);

    PreguntaDTO getPreguntaById(Integer id);

    PreguntaDTO createPregunta(PreguntaRequest preguntaRequest);

    PreguntaDTO updatePregunta(Integer id, PreguntaRequest preguntaRequest);

    void deletePregunta(Integer id);
}
