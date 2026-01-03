package unu.td.msacademico.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unu.td.msacademico.model.entity.EscuelaProfesionalModel;
import unu.td.msacademico.model.entity.FacultadModel;
import unu.td.msacademico.model.request.EscuelaProfesionalRequest;
import unu.td.msacademico.model.response.EscuelaProfesionalResponse;
import unu.td.msacademico.repository.IEscuelaProfesionalRepository;
import unu.td.msacademico.repository.IFacultadRepository;
import unu.td.msacademico.service.IEscuelaProfesionalService;
import unu.td.msacademico.utils.CatalogoUtils;
import unu.td.msacademico.utils.Mapper;
import unu.td.msacademico.utils.Messages;
import unu.td.msacademico.utils.exceptions.AlreadyExistsException;
import unu.td.msacademico.utils.exceptions.NotFoundException;

import java.util.List;

@AllArgsConstructor
@Service
public class EscuelaProfesionalService implements IEscuelaProfesionalService {

    private IEscuelaProfesionalRepository repository;
    private IFacultadRepository facultadRepository;
    private ModelMapper mapper;

    @Override
    public List<EscuelaProfesionalResponse> getAll() {
        return repository.findByEliminadoFalse()
                .stream()
                .map(model -> mapper.map(model, EscuelaProfesionalResponse.class))
                .toList();
    }

    @Override
    public EscuelaProfesionalResponse getById(Integer id) {
        EscuelaProfesionalModel escuela = checkExistsById(id);
        return mapper.map(escuela, EscuelaProfesionalResponse.class);
    }

    @Override
    public EscuelaProfesionalResponse add(EscuelaProfesionalRequest request) {
        checkExistsByNombre(request.getNombre(), 0);

        EscuelaProfesionalModel escuela = mapper.map(request, EscuelaProfesionalModel.class);
        escuela.setId(null);
        escuela.setFacultad(getFacultad(request.getIdFacultad()));

        escuela = repository.save(escuela);
        return mapper.map(escuela, EscuelaProfesionalResponse.class);
    }

    @Override
    public EscuelaProfesionalResponse update(Integer id, EscuelaProfesionalRequest request) {
        checkExistsByNombre(request.getNombre(), id);
        EscuelaProfesionalModel escuela = checkExistsById(id);

        escuela = Mapper.Escuela.requestToModel(escuela, request);
        escuela.setFacultad(getFacultad(request.getIdFacultad()));
        escuela = repository.save(escuela);

        return mapper.map(escuela, EscuelaProfesionalResponse.class);
    }

    @Override
    public void delete(Integer id) {
        checkExistsById(id);
        repository.softDelete(id);
    }

    @Override
    public void activate(Integer id) {
        checkExistsById(id);
        repository.activate(id);
    }

    @Override
    public void deactivate(Integer id) {
        checkExistsById(id);
        repository.deactivate(id);
    }

    @Override
    public List<EscuelaProfesionalResponse> getByIdFacultad(Integer idFacultad) {
        FacultadModel facultad = facultadRepository.findByIdAndEliminadoFalse(idFacultad).orElse(null);
        if (facultad == null) {
            throw new NotFoundException(Messages.NOT_FOUND_FACULTAD_BY_ID);
        }
        return repository.findByIdFacultadIdAndEliminadoFalse(idFacultad)
                .stream()
                .map(model -> mapper.map(model, EscuelaProfesionalResponse.class))
                .toList();
    }

    private FacultadModel getFacultad(Integer idFacultad) {
        FacultadModel facultad = facultadRepository.findByIdAndEliminadoFalse(idFacultad).orElse(null);
        if (facultad == null) {
            throw new NotFoundException(Messages.NOT_FOUND_FACULTAD_BY_ID);
        }
        return facultad;
    }

    private EscuelaProfesionalModel checkExistsById(Integer id) {
        EscuelaProfesionalModel escuela = repository.findByIdAndEliminadoFalse(id).orElse(null);
        if (escuela == null) {
            throw new NotFoundException(Messages.NOT_FOUND_ESCUELA_BY_ID);
        }

        return escuela;
    }

    private void checkExistsByNombre(String nombre, Integer id) {
        EscuelaProfesionalModel byNombre = repository.findByNombre(nombre);
        if (byNombre != null && !byNombre.getId().equals(id)) {
            if (byNombre.getEliminado()) {
                throw new AlreadyExistsException(Messages.ALREADY_EXISTS_ESCUELA_BY_NOMBRE_DEACTIVATE);
            }
            throw new AlreadyExistsException(Messages.ALREADY_EXISTS_ESCUELA_BY_NOMBRE);
        }
    }
}
