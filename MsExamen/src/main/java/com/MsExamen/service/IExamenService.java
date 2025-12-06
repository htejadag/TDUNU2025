package com.MsExamen.service;

import com.MsExamen.dto.ExamenDTO;
import com.MsExamen.dto.request.ExamenRequest;
import java.util.List;

public interface IExamenService {
    List<ExamenDTO> getAllExamenes();

    ExamenDTO getExamenById(Integer id);

    ExamenDTO createExamen(ExamenRequest examenRequest);

    ExamenDTO updateExamen(Integer id, ExamenRequest examenRequest);

    void deleteExamen(Integer id);
}
