package unu.td.MsAcademico.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unu.td.MsAcademico.model.entity.FacultadModel;
import unu.td.MsAcademico.model.response.FacultadResponse;
import unu.td.MsAcademico.model.request.FacultadRequest;
import unu.td.MsAcademico.repository.IFacultadRepository;
import unu.td.MsAcademico.service.IFacultadService;

import java.util.List;

@AllArgsConstructor
@Service
public class FacultadService implements IFacultadService {

    private IFacultadRepository repository;
    private ModelMapper mapper;

    @Override
    public List<FacultadResponse> getAll() {
        return List.of();
    }

    @Override
    public FacultadResponse getById(Integer id) {
        return null;
    }

    @Override
    public FacultadResponse add(FacultadRequest request) {
        FacultadModel facultad = mapper.map(request, FacultadModel.class);
        facultad.setUsuarioCreacion(1);

        FacultadModel nuevaFacultad = repository.save(facultad);

        FacultadResponse response = mapper.map(nuevaFacultad, FacultadResponse.class);

        return response;
    }

    @Override
    public FacultadResponse edit(Integer id, FacultadRequest request) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
