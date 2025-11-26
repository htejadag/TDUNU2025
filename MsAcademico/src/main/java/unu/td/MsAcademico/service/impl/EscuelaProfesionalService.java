package unu.td.MsAcademico.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unu.td.MsAcademico.model.entity.EscuelaProfesionalModel;
import unu.td.MsAcademico.model.entity.FacultadModel;
import unu.td.MsAcademico.model.request.EscuelaProfesionalRequest;
import unu.td.MsAcademico.model.response.EscuelaProfesionalResponse;
import unu.td.MsAcademico.model.response.FacultadResponse;
import unu.td.MsAcademico.repository.IEscuelaProfesionalRepository;
import unu.td.MsAcademico.repository.IFacultadRepository;
import unu.td.MsAcademico.service.IEscuelaProfesionalService;
import unu.td.MsAcademico.utils.Mapper;
import unu.td.MsAcademico.utils.Messages;
import unu.td.MsAcademico.utils.exceptions.AlreadyExistsException;
import unu.td.MsAcademico.utils.exceptions.NotFoundException;

import java.util.List;

@AllArgsConstructor
@Service
public class EscuelaProfesionalService implements IEscuelaProfesionalService {

    private IEscuelaProfesionalRepository repository;
    private IFacultadRepository facultadRepository;
    private ModelMapper mapper;

    @Override
    public List<EscuelaProfesionalResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(escuela -> getResponse(escuela))
                .toList();
    }

    @Override
    public EscuelaProfesionalResponse getById(Integer id) {
        EscuelaProfesionalModel escuela = repository.findById(id).orElse(null);
        if (escuela == null) {
            throw new NotFoundException(Messages.NOT_FOUND_ESCUELA_BY_ID);
        }

        return getResponse(escuela);
    }

    @Override
    public EscuelaProfesionalResponse add(EscuelaProfesionalRequest request) {
        EscuelaProfesionalModel byNombre = repository.findByNombre(request.getNombre());
        if (byNombre != null) {
            throw new AlreadyExistsException(Messages.ALREADY_EXISTS_ESCUELA_BY_NOMBRE);
        }

        EscuelaProfesionalModel escuela = Mapper.Escuela.requestToModel(request);
        escuela.setFacultad(getFacultad(request.getIdFacultad()));
        escuela.setUsuarioCreacion(1);

        escuela = repository.save(escuela);
        return getResponse(escuela);
    }

    @Override
    public EscuelaProfesionalResponse update(Integer id, EscuelaProfesionalRequest request) {
        EscuelaProfesionalModel escuela = repository.findById(id).orElse(null);
        if (escuela == null) {
            throw new NotFoundException(Messages.NOT_FOUND_ESCUELA_BY_ID);
        }

        EscuelaProfesionalModel byNombre = repository.findByNombre(request.getNombre());
        if (byNombre != null) {
            throw new AlreadyExistsException(Messages.ALREADY_EXISTS_ESCUELA_BY_NOMBRE);
        }

        escuela.setNombre(request.getNombre());
        escuela = repository.save(escuela);

        return getResponse(escuela);
    }

    @Override
    public void delete(Integer id) {
        EscuelaProfesionalModel escuela = repository.findById(id).orElse(null);
        if (escuela == null) {
            throw new NotFoundException(Messages.NOT_FOUND_ESCUELA_BY_ID);
        }
        repository.deleteById(id);
    }

    private EscuelaProfesionalResponse getResponse(EscuelaProfesionalModel escuela) {
        EscuelaProfesionalResponse response = mapper.map(escuela, EscuelaProfesionalResponse.class);
//        response.setFacultad(mapper.map(facultad, FacultadResponse.class));
        return response;
    }

    private FacultadModel getFacultad(Integer idFacultad) {
        FacultadModel facultad = facultadRepository.findById(idFacultad).orElse(null);
        if (facultad == null) {
            throw new NotFoundException(Messages.NOT_FOUND_FACULTAD_BY_ID);
        }
        return facultad;
    }
}
