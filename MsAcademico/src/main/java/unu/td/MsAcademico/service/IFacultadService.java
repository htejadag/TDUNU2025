package unu.td.MsAcademico.service;

import unu.td.MsAcademico.model.entity.FacultadModel;
import unu.td.MsAcademico.model.request.FacultadRequest;

import java.util.List;

public interface IFacultadService {

    public List<FacultadModel> getAll();

    public FacultadModel getById(Integer id);

    public FacultadModel add(FacultadRequest request);

    public FacultadModel edit(Integer id, FacultadRequest request);

    public void delete(Integer id);

}
