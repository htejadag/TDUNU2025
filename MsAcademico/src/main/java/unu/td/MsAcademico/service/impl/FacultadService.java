package unu.td.MsAcademico.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unu.td.MsAcademico.model.entity.FacultadModel;
import unu.td.MsAcademico.model.response.FacultadResponse;
import unu.td.MsAcademico.model.request.FacultadRequest;
import unu.td.MsAcademico.repository.IFacultadRepository;
import unu.td.MsAcademico.service.IFacultadService;
import unu.td.MsAcademico.utils.Messages;
import unu.td.MsAcademico.utils.exceptions.AlreadyExistsException;
import unu.td.MsAcademico.utils.exceptions.NotFoundException;

import java.util.List;

@AllArgsConstructor
@Service
public class FacultadService implements IFacultadService {

    private IFacultadRepository repository;
    private ModelMapper mapper;

    @Override
    public List<FacultadResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(model -> mapper.map(model, FacultadResponse.class))
                .toList();
    }

    @Override
    public FacultadResponse getById(Integer id) {
        FacultadModel facultad = repository.findById(id).orElse(null);
        if (facultad == null) {
            throw new NotFoundException(Messages.NOT_FOUND_FACULTAD_BY_ID);
        }
        return mapper.map(facultad, FacultadResponse.class);
    }

    @Override
    public FacultadResponse add(FacultadRequest request) {
        FacultadModel byNombre = repository.findByNombre(request.getNombre());
        if (byNombre != null) {
            throw new AlreadyExistsException(Messages.ALREADY_EXISTS_FACULTAD_BY_NOMBRE);
        }

        FacultadModel facultad = mapper.map(request, FacultadModel.class);
        facultad.setUsuarioCreacion(1);

        facultad = repository.save(facultad);
        return mapper.map(facultad, FacultadResponse.class);
    }

    @Override
    public FacultadResponse update(Integer id, FacultadRequest request) {
        FacultadModel facultad = repository.findById(id).orElse(null);
        if (facultad == null) {
            throw new NotFoundException(Messages.NOT_FOUND_FACULTAD_BY_ID);
        }

        FacultadModel byNombre = repository.findByNombre(request.getNombre());
        if (byNombre != null) {
            throw new AlreadyExistsException(Messages.ALREADY_EXISTS_FACULTAD_BY_NOMBRE);
        }

        facultad.setNombre(request.getNombre());
        facultad = repository.save(facultad);

        return mapper.map(facultad, FacultadResponse.class);
    }

    @Override
    public void delete(Integer id) {
        FacultadModel facultad = repository.findById(id).orElse(null);
        if (facultad == null) {
            throw new NotFoundException(Messages.NOT_FOUND_FACULTAD_BY_ID);
        }
        repository.deleteById(id);
    }
}
