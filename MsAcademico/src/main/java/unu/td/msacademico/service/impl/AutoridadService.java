package unu.td.msacademico.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unu.td.msacademico.model.entity.AutoridadModel;
import unu.td.msacademico.model.entity.CatalogoModel;
import unu.td.msacademico.model.entity.EscuelaProfesionalModel;
import unu.td.msacademico.model.entity.FacultadModel;
import unu.td.msacademico.model.request.AutoridadRequest;
import unu.td.msacademico.model.response.*;
import unu.td.msacademico.repository.IAutoridadRepository;
import unu.td.msacademico.repository.ICatalogoRepository;
import unu.td.msacademico.repository.IEscuelaProfesionalRepository;
import unu.td.msacademico.repository.IFacultadRepository;
import unu.td.msacademico.service.IAutoridadService;
import unu.td.msacademico.utils.CatalogoUtils;
import unu.td.msacademico.utils.Mapper;
import unu.td.msacademico.utils.Messages;
import unu.td.msacademico.utils.exceptions.BadRequestException;
import unu.td.msacademico.utils.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class AutoridadService implements IAutoridadService {

    private IAutoridadRepository repository;
    private ICatalogoRepository catalogoRepository;
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
        return getResponse(autoridad, null);
    }

    @Override
    public AutoridadResponse add(AutoridadRequest request) {
        checkFechaInicioAndFechaFin(request.getFechaInicio(), request.getFechaFin());
        AutoridadModel autoridad = Mapper.Autoridad.requestToModel(null, request);

        autoridad.setTipoAutoridad(getTipo(request.getIdTipoAutoridad()));
        autoridad.setTipoEntidad(getTipo(request.getIdTipoEntidad()));
        autoridad.setUsuarioCreacion(CatalogoUtils.IdUsuarioCreacion);
        autoridad = repository.save(autoridad);

        return getResponse(autoridad, null);
    }

    @Override
    public AutoridadResponse update(Integer id, AutoridadRequest request) {
        checkFechaInicioAndFechaFin(request.getFechaInicio(), request.getFechaFin());
        AutoridadModel autoridad = checkExistsById(id);

        autoridad = Mapper.Autoridad.requestToModel(autoridad, request);
        autoridad.setTipoAutoridad(getTipo(request.getIdTipoAutoridad()));
        autoridad.setTipoEntidad(getTipo(request.getIdTipoEntidad()));
        autoridad.setUsuarioModificacion(CatalogoUtils.IdUsuarioModificacion);
        autoridad = repository.save(autoridad);

        return getResponse(autoridad, null);
    }

    @Override
    public void delete(Integer id) {
        checkExistsById(id);
        repository.softDelete(id, CatalogoUtils.IdUsuarioModificacion);
    }

    @Override
    public void activate(Integer id) {
        checkExistsById(id);
        repository.activate(id, CatalogoUtils.IdUsuarioModificacion);
    }

    @Override
    public void deactivate(Integer id) {
        checkExistsById(id);
        repository.deactivate(id, CatalogoUtils.IdUsuarioModificacion);
    }

    @Override
    public List<AutoridadResponse> getByEntidad(Integer idTipoEntidad, Integer idEntidad) {
        checkIdTipoEntidad(idTipoEntidad);
        EntidadAcademicaResponse entidad = getEntidadResponse(idTipoEntidad, idEntidad);
        List<AutoridadModel> autoridades = repository.findByEntidad(idTipoEntidad, idEntidad);
        return autoridades.stream()
                .map(model -> getResponse(model, entidad))
                .toList();
    }

    @Override
    public AutoridadResponse getByEntidadAndFecha(Integer idTipoEntidad, Integer idEntidad, LocalDate fecha) {
        checkIdTipoEntidad(idTipoEntidad);
        EntidadAcademicaResponse entidad = getEntidadResponse(idTipoEntidad, idEntidad);
        AutoridadModel autoridad = repository.findByEntidadAndFecha(idTipoEntidad, idEntidad, fecha).orElse(null);
        if (autoridad == null) {
            throw new NotFoundException(Messages.NOT_FOUND_AUTORIDAD);
        }
        return getResponse(autoridad, entidad);
    }

    private AutoridadModel checkExistsById(Integer id) {
        AutoridadModel autoridad = repository.findByIdAndEliminadoFalse(id).orElse(null);
        if (autoridad == null) {
            throw new NotFoundException(Messages.NOT_FOUND_AUTORIDAD_BY_ID);
        }

        return autoridad;
    }

    private AutoridadResponse getResponse(AutoridadModel model, EntidadAcademicaResponse entidad) {
        AutoridadResponse response = mapper.map(model, AutoridadResponse.class);
        response.setEntidad(entidad);
        if (entidad == null) {
            EntidadAcademicaResponse entidadResponse = getEntidadResponse(model.getTipoEntidad().getId(), model.getIdEntidad());
            response.setEntidad(entidadResponse);
        }

        return response;
    }

    private EntidadAcademicaResponse getEntidadResponse(Integer idTipoEntidad, Integer idEntidad) {
        if (idTipoEntidad.equals(CatalogoUtils.ID_TIPO_ENTIDAD_FACULTAD)) {
            FacultadModel facultad = facultadRepository.findByIdAndEliminadoFalse(idEntidad).orElse(null);
            if (facultad == null) {
                throw new NotFoundException(Messages.NOT_FOUND_FACULTAD_BY_ID);
            }
            return mapper.map(facultad, FacultadResponse.class);
        } else if (idTipoEntidad.equals(CatalogoUtils.ID_TIPO_ENTIDAD_ESCUELA)) {
            EscuelaProfesionalModel escuela = escuelaRepository.findByIdAndEliminadoFalse(idEntidad).orElse(null);
            if (escuela == null) {
                throw new NotFoundException(Messages.NOT_FOUND_ESCUELA_BY_ID);
            }
            return mapper.map(escuela, EscuelaProfesionalResponse.class);
        } else {
            throw new NotFoundException(Messages.NOT_FOUND_TIPO_ENTIDAD_BY_ID);
        }
    }

    public void checkFechaInicioAndFechaFin(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaFin != null) {
            if (fechaInicio.isAfter(fechaFin)) {
                throw new BadRequestException(Messages.INVALID_FECHA_INICIO);
            }
        }
    }

    public void checkIdTipoEntidad(Integer idTipoEntidad) {
        if (idTipoEntidad.intValue() != CatalogoUtils.ID_TIPO_ENTIDAD_FACULTAD && idTipoEntidad.intValue() != CatalogoUtils.ID_TIPO_ENTIDAD_ESCUELA) {
            throw new NotFoundException(Messages.NOT_FOUND_TIPO_ENTIDAD_BY_ID);
        }
    }

    public CatalogoModel getTipo(Integer idTipo) {
        CatalogoModel model = catalogoRepository.findById(idTipo).orElse(null);
        if (model == null) {
            throw new NotFoundException(Messages.NOT_FOUND_CATALOGO_BY_ID);
        }
        return model;
    }

}
