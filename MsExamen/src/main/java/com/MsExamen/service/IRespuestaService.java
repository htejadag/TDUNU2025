package com.MsExamen.service;

import com.MsExamen.dto.RespuestaDTO;
import com.MsExamen.dto.request.RespuestaRequest;
import java.util.List;

public interface IRespuestaService {
    List<RespuestaDTO> getAllRespuestas();

    List<RespuestaDTO> getRespuestasByPreguntaId(Integer idPregunta);

    RespuestaDTO getRespuestaById(Integer id);

    RespuestaDTO createRespuesta(RespuestaRequest respuestaRequest);

    RespuestaDTO updateRespuesta(Integer id, RespuestaRequest respuestaRequest);

    void deleteRespuesta(Integer id);
}
