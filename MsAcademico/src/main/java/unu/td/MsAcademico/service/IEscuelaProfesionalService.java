package unu.td.MsAcademico.service;

import unu.td.MsAcademico.model.request.EscuelaProfesionalRequest;
import unu.td.MsAcademico.model.response.EscuelaProfesionalResponse;
import unu.td.MsAcademico.utils.IService;

import java.util.List;

public interface IEscuelaProfesionalService extends IService<EscuelaProfesionalResponse, EscuelaProfesionalRequest> {

    List<EscuelaProfesionalResponse> getByIdFacultad(Integer idFacultad);

}
