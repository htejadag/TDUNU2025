package unu.td.MsAcademico.service;

import unu.td.MsAcademico.model.response.FacultadResponse;
import unu.td.MsAcademico.model.request.FacultadRequest;

import java.util.List;

public interface IFacultadService {

    public List<FacultadResponse> getAll();

    public FacultadResponse getById(Integer id);

    public FacultadResponse add(FacultadRequest request);

    public FacultadResponse update(Integer id, FacultadRequest request);

    public void delete(Integer id);

    public void deactivate(Integer id);

}
