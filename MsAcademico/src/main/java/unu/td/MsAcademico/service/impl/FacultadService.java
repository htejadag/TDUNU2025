package unu.td.MsAcademico.service.impl;

import org.springframework.stereotype.Service;
import unu.td.MsAcademico.model.entity.FacultadModel;
import unu.td.MsAcademico.model.request.FacultadRequest;
import unu.td.MsAcademico.service.IFacultadService;

import java.util.List;

@Service
public class FacultadService implements IFacultadService {
    @Override
    public List<FacultadModel> getAll() {
        return List.of();
    }

    @Override
    public FacultadModel getById(Integer id) {
        return null;
    }

    @Override
    public FacultadModel add(FacultadRequest request) {
        return null;
    }

    @Override
    public FacultadModel edit(Integer id, FacultadRequest request) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
