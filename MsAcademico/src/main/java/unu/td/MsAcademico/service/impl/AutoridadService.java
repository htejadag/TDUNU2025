package unu.td.MsAcademico.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unu.td.MsAcademico.model.entity.AutoridadModel;
import unu.td.MsAcademico.model.entity.EscuelaProfesionalModel;
import unu.td.MsAcademico.model.entity.FacultadModel;
import unu.td.MsAcademico.model.request.AutoridadRequest;
import unu.td.MsAcademico.model.response.AutoridadResponse;
import unu.td.MsAcademico.model.response.EntidadAcademicaResponse;
import unu.td.MsAcademico.model.response.EscuelaProfesionalResponse;
import unu.td.MsAcademico.model.response.FacultadResponse;
import unu.td.MsAcademico.repository.IAutoridadRepository;
import unu.td.MsAcademico.repository.IEscuelaProfesionalRepository;
import unu.td.MsAcademico.repository.IFacultadRepository;
import unu.td.MsAcademico.service.IAutoridadService;
import unu.td.MsAcademico.utils.CatalogoId;
import unu.td.MsAcademico.utils.Mapper;
import unu.td.MsAcademico.utils.Messages;
import unu.td.MsAcademico.utils.exceptions.NotFoundException;

import java.util.List;

@AllArgsConstructor
@Service
public class AutoridadService implements IAutoridadService {

    private IAutoridadRepository repository;
    private IFacultadRepository facultadRepository;
    private IEscuelaProfesionalRepository escuelaRepository;
    private ModelMapper mapper;

    @Override
    public List<AutoridadResponse> getAll() {
        return repository.findByEliminadoFalse()
                .stream()
                .map(model -> mapper.map(model, AutoridadResponse.class))
                .toList();
    }

    @Override
    public AutoridadResponse getById(Integer id) {
        AutoridadModel autoridad = checkExistsById(id);
        return mapper.map(autoridad, AutoridadResponse.class);
    }

    @Override
    public AutoridadResponse add(AutoridadRequest request) {
        AutoridadModel autoridad = Mapper.Autoridad.requestToModel(request);
        autoridad.setUsuarioCreacion("dbd2a268-a9b0-42ba-981d-3977361f11f5");

        autoridad = repository.save(autoridad);
        return getResponse(autoridad);
    }

    @Override
    public AutoridadResponse update(Integer id, AutoridadRequest request) {
        AutoridadModel autoridad = checkExistsById(id);
        autoridad.setUsuarioModificacion("a74c0747-1151-455c-87e2-2298e554521f");
        autoridad = repository.save(autoridad);

        return getResponse(autoridad);
    }

    @Override
    public void delete(Integer id) {
        checkExistsById(id);
        repository.delete(id, "a74c0747-1151-455c-87e2-2298e554521f");
    }

    @Override
    public void activate(Integer id) {

    }

    @Override
    public void deactivate(Integer id) {

    }

    private AutoridadModel checkExistsById(Integer id) {
        AutoridadModel autoridad = repository.findByIdAndEliminadoFalse(id).orElse(null);
        if (autoridad == null) {
            throw new NotFoundException(Messages.NOT_FOUND_FACULTAD_BY_ID);
        }

        return autoridad;
    }

    private AutoridadResponse getResponse(AutoridadModel model) {
        AutoridadResponse response = mapper.map(model, AutoridadResponse.class);
        EntidadAcademicaResponse entidadResponse = getEntidadResponse(model.getIdTipoEntidad(), model.getIdEntidad());
        response.setEntidad(entidadResponse);
        if (model.getIdTipoEntidad().intValue() == CatalogoId.ID_TIPO_ENTIDAD_FACULTAD) {
            response.setTipoEntidad("Facultad");
        } else if (model.getIdTipoEntidad().intValue() == CatalogoId.ID_TIPO_ENTIDAD_ESCUELA) {
            response.setTipoEntidad("Escuela Profesional");
        }
        return response;
    }

    private EntidadAcademicaResponse getEntidadResponse(Integer idTipoEntidad, Integer idEntidad) {
        if (idTipoEntidad.intValue() == CatalogoId.ID_TIPO_ENTIDAD_FACULTAD) {
            FacultadModel facultad = facultadRepository.findByIdAndEliminadoFalse(idEntidad).orElse(null);
            if (facultad == null) {
                throw new NotFoundException(Messages.NOT_FOUND_FACULTAD_BY_ID);
            }
            return mapper.map(facultad, FacultadResponse.class);
        } else if (idTipoEntidad.intValue() == CatalogoId.ID_TIPO_ENTIDAD_ESCUELA) {
            EscuelaProfesionalModel escuela = escuelaRepository.findByIdAndEliminadoFalse(idEntidad).orElse(null);
            if (escuela == null) {
                throw new NotFoundException(Messages.NOT_FOUND_ESCUELA_BY_ID);
            }
            return mapper.map(escuela, EscuelaProfesionalResponse.class);
        } else {
            throw new NotFoundException(Messages.NOT_FOUND_TIPO_ENTIDAD);
        }
    }

}
