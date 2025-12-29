package unu.td.msacademico.service;

import unu.td.msacademico.model.request.EscuelaProfesionalRequest;
import unu.td.msacademico.model.response.EscuelaProfesionalResponse;
import unu.td.msacademico.utils.IService;

import java.util.List;

public interface IEscuelaProfesionalService extends IService<EscuelaProfesionalResponse, EscuelaProfesionalRequest> {

    List<EscuelaProfesionalResponse> getByIdFacultad(Integer idFacultad);

}
