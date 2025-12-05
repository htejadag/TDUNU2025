package unu.td.MsAcademico.service;

import unu.td.MsAcademico.model.request.EscuelaProfesionalRequest;
import unu.td.MsAcademico.model.response.EscuelaProfesionalResponse;

import java.util.List;

public interface IEscuelaProfesionalService {

    public List<EscuelaProfesionalResponse> getAll();

    public EscuelaProfesionalResponse getById(Integer id);

    public EscuelaProfesionalResponse add(EscuelaProfesionalRequest request);

    public EscuelaProfesionalResponse update(Integer id, EscuelaProfesionalRequest request);

    public void delete(Integer id);

}
