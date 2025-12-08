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
        return repository.findByEliminadoFalse()
                .stream()
                .map(model -> mapper.map(model, FacultadResponse.class))
                .toList();
    }

    @Override
    public FacultadResponse getById(Integer id) {
        FacultadModel facultad = checkExistsById(id);
        return mapper.map(facultad, FacultadResponse.class);
    }

    @Override
    public FacultadResponse add(FacultadRequest request) {
        checkExistsByNombre(request.getNombre(), 0);

        FacultadModel facultad = mapper.map(request, FacultadModel.class);
        facultad.setUsuarioCreacion("dbd2a268-a9b0-42ba-981d-3977361f11f5");

        facultad = repository.save(facultad);
        return mapper.map(facultad, FacultadResponse.class);
    }

    @Override
    public FacultadResponse update(Integer id, FacultadRequest request) {
        FacultadModel facultad = checkExistsById(id);
        checkExistsByNombre(request.getNombre(), facultad.getId());

        facultad.setNombre(request.getNombre());
        facultad.setUsuarioModificacion("a74c0747-1151-455c-87e2-2298e554521f");
        facultad = repository.save(facultad);

        return mapper.map(facultad, FacultadResponse.class);
    }

    @Override
    public void delete(Integer id) {
//        FacultadModel facultad = repository.findById(id).orElse(null);
//        if (facultad == null) {
//            throw new NotFoundException(Messages.NOT_FOUND_FACULTAD_BY_ID);
//        }
//
//        facultad.setEliminado(Boolean.FALSE);
//        facultad.setUsuarioModificacion("a74c0747-1151-455c-87e2-2298e554521f");
//        repository.save(facultad);

        checkExistsById(id);
        repository.delete(id, "a74c0747-1151-455c-87e2-2298e554521f");
    }

    @Override
    public void activate(Integer id) {

    }

    @Override
    public void deactivate(Integer id) {

    }

    private FacultadModel checkExistsById(Integer id) {
        FacultadModel facultad = repository.findByIdAndEliminadoFalse(id).orElse(null);
        if (facultad == null) {
            throw new NotFoundException(Messages.NOT_FOUND_FACULTAD_BY_ID);
        }

        return facultad;
    }

    private void checkExistsByNombre(String nombre, Integer id) {
        FacultadModel byNombre = repository.findByNombre(nombre);
        if (byNombre != null && !byNombre.getEliminado()) {
            throw new AlreadyExistsException(Messages.ALREADY_EXISTS_FACULTAD_BY_NOMBRE_DEACTIVATE); //pero ya no tiene reversa desde el sistema, debe tocar la db??
        }
        if (byNombre != null && byNombre.getId() != id) {
            throw new AlreadyExistsException(Messages.ALREADY_EXISTS_FACULTAD_BY_NOMBRE);
        }
    }
}
