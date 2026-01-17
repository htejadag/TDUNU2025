package unu.td.MsAcademico.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import unu.td.MsAcademico.model.entity.EscuelaProfesionalModel;
import unu.td.MsAcademico.model.entity.FacultadModel;
import unu.td.MsAcademico.model.request.EscuelaProfesionalRequest;
import unu.td.MsAcademico.model.response.EscuelaProfesionalResponse;
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
    @Cacheable(value = "escuela", key = "'all'", cacheManager = "listCacheManager")
    public List<EscuelaProfesionalResponse> getAll() {
        return repository.findByEliminadoFalse()
                .stream()
                .map(model -> mapper.map(model, EscuelaProfesionalResponse.class))
                .toList();
    }

    @Override
    @Cacheable(value = "escuelaId", key = "#id", cacheManager = "objectCacheManager", unless = "#result == null")
    public EscuelaProfesionalResponse getById(Integer id) {
        EscuelaProfesionalModel escuela = checkExistsById(id);
        return mapper.map(escuela, EscuelaProfesionalResponse.class);
    }

    @Override
    @CachePut(value = "escuelaId", key = "#result.id", cacheManager = "objectCacheManager")
    @CacheEvict(value = { "escuela", "escuelaByFacultad" }, allEntries = true)
    public EscuelaProfesionalResponse add(EscuelaProfesionalRequest request) {
        checkExistsByNombre(request.getNombre(), 0);

        EscuelaProfesionalModel escuela = mapper.map(request, EscuelaProfesionalModel.class);
        escuela.setId(null);
        escuela.setFacultad(getFacultad(request.getIdFacultad()));

        escuela = repository.save(escuela);
        return mapper.map(escuela, EscuelaProfesionalResponse.class);
    }

    @Override
    @CachePut(value = "escuelaId", key = "#id", cacheManager = "objectCacheManager")
    @CacheEvict(value = { "escuela", "escuelaByFacultad" }, allEntries = true)
    public EscuelaProfesionalResponse update(Integer id, EscuelaProfesionalRequest request) {
        checkExistsByNombre(request.getNombre(), id);
        EscuelaProfesionalModel escuela = checkExistsById(id);

        escuela = Mapper.Escuela.requestToModel(escuela, request);
        escuela.setFacultad(getFacultad(request.getIdFacultad()));
        escuela = repository.save(escuela);

        return mapper.map(escuela, EscuelaProfesionalResponse.class);
    }

    @Override
    @CacheEvict(value = { "escuelaId", "escuela", "escuelaByFacultad" }, allEntries = true)
    public void delete(Integer id) {
        checkExistsById(id);
        repository.softDelete(id);
    }

    @Override
    @CacheEvict(value = { "escuelaId", "escuela", "escuelaByFacultad" }, allEntries = true)
    public void activate(Integer id) {
        checkExistsById(id);
        repository.activate(id);
    }

    @Override
    @CacheEvict(value = { "escuelaId", "escuela", "escuelaByFacultad" }, allEntries = true)
    public void deactivate(Integer id) {
        checkExistsById(id);
        repository.deactivate(id);
    }

    @Override
    @Cacheable(value = "escuelaByFacultad", key = "#idFacultad", cacheManager = "listCacheManager")
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
