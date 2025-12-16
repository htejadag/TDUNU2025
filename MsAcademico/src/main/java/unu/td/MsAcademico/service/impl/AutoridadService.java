package unu.td.MsAcademico.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unu.td.MsAcademico.model.entity.AutoridadModel;
import unu.td.MsAcademico.model.entity.CatalogoModel;
import unu.td.MsAcademico.model.entity.EscuelaProfesionalModel;
import unu.td.MsAcademico.model.entity.FacultadModel;
import unu.td.MsAcademico.model.request.AutoridadRequest;
import unu.td.MsAcademico.model.response.*;
import unu.td.MsAcademico.repository.IAutoridadRepository;
import unu.td.MsAcademico.repository.ICatalogoRepository;
import unu.td.MsAcademico.repository.IEscuelaProfesionalRepository;
import unu.td.MsAcademico.repository.IFacultadRepository;
import unu.td.MsAcademico.service.IAutoridadService;
import unu.td.MsAcademico.utils.CatalogoUtils;
import unu.td.MsAcademico.utils.Mapper;
import unu.td.MsAcademico.utils.Messages;
import unu.td.MsAcademico.utils.exceptions.AlreadyExistsException;
import unu.td.MsAcademico.utils.exceptions.BadRequestException;
import unu.td.MsAcademico.utils.exceptions.NotFoundException;

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
        checkParameters(request);
        AutoridadModel autoridad = Mapper.Autoridad.requestToModel(null, request);
        CatalogoModel tipoAutoridad = getTipoAutoridad(request.getIdTipoAutoridad());
        CatalogoModel tipoEntidad = getTipoEntidad(request.getIdTipoEntidad());

        autoridad.setTipoAutoridad(tipoAutoridad);
        autoridad.setTipoEntidad(tipoEntidad);
        autoridad.setUsuarioCreacion(CatalogoUtils.IdUsuarioCreacion);
        autoridad = repository.save(autoridad);

        return getResponse(autoridad, null);
    }

    @Override
    public AutoridadResponse update(Integer id, AutoridadRequest request) {
        checkParameters(request);
        AutoridadModel autoridad = checkExistsById(id);
        CatalogoModel tipoAutoridad = getTipoAutoridad(request.getIdTipoAutoridad());
        CatalogoModel tipoEntidad = getTipoEntidad(request.getIdTipoEntidad());

        autoridad = Mapper.Autoridad.requestToModel(autoridad, request);
        autoridad.setTipoAutoridad(tipoAutoridad);
        autoridad.setTipoEntidad(tipoEntidad);
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
        getTipoEntidad(idTipoEntidad);
        EntidadAcademicaResponse entidad = getEntidadResponse(idTipoEntidad, idEntidad);
        List<AutoridadModel> autoridades = repository.findByEntidad(idTipoEntidad, idEntidad);
        return autoridades.stream()
                .map(model -> getResponse(model, entidad))
                .toList();
    }

    @Override
    public AutoridadResponse getByEntidadAndFecha(Integer idTipoEntidad, Integer idEntidad, LocalDate fecha) {
        getTipoEntidad(idTipoEntidad);
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

    public void checkValidFechaInicioAndFechaFin(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaFin != null && fechaInicio.isAfter(fechaFin)) {
            throw new BadRequestException(Messages.INVALID_FECHA_INICIO);
        }
    }

    public CatalogoModel getTipoEntidad(Integer idTipoEntidad) {
        if (!idTipoEntidad.equals(CatalogoUtils.ID_TIPO_ENTIDAD_FACULTAD) && !idTipoEntidad.equals(CatalogoUtils.ID_TIPO_ENTIDAD_ESCUELA)) {
            throw new NotFoundException(Messages.NOT_FOUND_TIPO_ENTIDAD_BY_ID);
        }
        CatalogoModel model = catalogoRepository.findById(idTipoEntidad).orElse(null);
        if (model == null) {
            throw new NotFoundException(Messages.NOT_FOUND_TIPO_AUTORIDAD_BY_ID);
        }
        return model;
    }

    public CatalogoModel getTipo(Integer idTipo) {
        CatalogoModel model = catalogoRepository.findById(idTipo).orElse(null);
        if (model == null) {
            throw new NotFoundException(Messages.NOT_FOUND_CATALOGO_BY_ID);
        }
        return model;
    }

    private void checkAutoridadByTipoEntidad(Integer idTipoEntidad, Integer idTipoAutoridad) {
        if (idTipoEntidad.equals(CatalogoUtils.ID_TIPO_ENTIDAD_FACULTAD) && !idTipoAutoridad.equals(CatalogoUtils.ID_TIPO_AUTORIDAD_DECANO)) {
            throw new BadRequestException(Messages.INVALID_AUTORIDAD_FACULTAD);
        }

        if (idTipoEntidad.equals(CatalogoUtils.ID_TIPO_ENTIDAD_ESCUELA) && !idTipoAutoridad.equals(CatalogoUtils.ID_TIPO_AUTORIDAD_DIRECTOR)) {
            throw new BadRequestException(Messages.INVALID_AUTORIDAD_ESCUELA);
        }
    }

    public CatalogoModel getTipoAutoridad(Integer idTipoAutoridad) {
        if (!idTipoAutoridad.equals(CatalogoUtils.ID_TIPO_AUTORIDAD_DECANO) && !idTipoAutoridad.equals(CatalogoUtils.ID_TIPO_AUTORIDAD_DIRECTOR)) {
            throw new NotFoundException(Messages.NOT_FOUND_TIPO_AUTORIDAD_BY_ID);
        }
        CatalogoModel model = catalogoRepository.findById(idTipoAutoridad).orElse(null);
        if (model == null) {
            throw new NotFoundException(Messages.NOT_FOUND_TIPO_AUTORIDAD_BY_ID);
        }
        return model;
    }

    public void checkParameters(AutoridadRequest request) {
        Boolean existe = repository.checkParameters(request.getIdTipoEntidad(), request.getIdEntidad(), request.getFechaInicio(), request.getFechaFin());
        if (existe) {
            throw new AlreadyExistsException(Messages.ALREADY_EXISTS_AUTORIDAD_BY_PARAMETERS);
        }
        checkValidFechaInicioAndFechaFin(request.getFechaInicio(), request.getFechaFin());
        checkAutoridadByTipoEntidad(request.getIdTipoEntidad(), request.getIdTipoAutoridad());
    }

}
