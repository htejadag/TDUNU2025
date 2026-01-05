package unu.td.MsAcademico.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unu.td.MsAcademico.model.entity.CatalogoModel;
import unu.td.MsAcademico.model.request.CatalogoRequest;
import unu.td.MsAcademico.model.response.CatalogoResponse;
import unu.td.MsAcademico.repository.ICatalogoRepository;
import unu.td.MsAcademico.service.ICatalogoService;
import unu.td.MsAcademico.utils.Mapper;
import unu.td.MsAcademico.utils.Messages;
import unu.td.MsAcademico.utils.exceptions.AlreadyExistsException;
import unu.td.MsAcademico.utils.exceptions.NotFoundException;

import java.util.List;

@AllArgsConstructor
@Service
public class CatalogoService implements ICatalogoService {

    private ICatalogoRepository repository;
    private ModelMapper mapper;

    @Override
    public List<CatalogoResponse> getAllByCategoria(String categoria) {
        return repository.findByCategoriaAndEliminadoFalse(categoria)
                .stream()
                .map(model -> mapper.map(model, CatalogoResponse.class))
                .toList();
    }

    @Override
    public List<CatalogoResponse> getAll() {
        return repository.findByEliminadoFalse()
                .stream()
                .map(model -> mapper.map(model, CatalogoResponse.class))
                .toList();
    }

    @Override
    public CatalogoResponse getById(Integer id) {
        CatalogoModel catalogo = checkExistsById(id);
        return mapper.map(catalogo, CatalogoResponse.class);
    }

    @Override
    public CatalogoResponse add(CatalogoRequest request) {
        checkParameters(request, 0);

        CatalogoModel catalogo = mapper.map(request, CatalogoModel.class);
        Integer codigo = getLastCodigoByCategoria(request.getCategoria());
        catalogo.setCodigo(codigo);
        catalogo = repository.save(catalogo);

        return mapper.map(catalogo, CatalogoResponse.class);
    }

    @Override
    public CatalogoResponse update(Integer id, CatalogoRequest request) {
        CatalogoModel catalogo = checkExistsById(id);
        checkParameters(request, catalogo.getId());

        catalogo = Mapper.Catalogo.requestToModel(catalogo, request);
        catalogo = repository.save(catalogo);

        return mapper.map(catalogo, CatalogoResponse.class);
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

    private CatalogoModel checkExistsById(Integer id) {
        CatalogoModel catalogo = repository.findByIdAndEliminadoFalse(id).orElse(null);
        if (catalogo == null) {
            throw new NotFoundException(Messages.NOT_FOUND_CATALOGO_BY_ID);
        }

        return catalogo;
    }

    private void checkExistsByCategoriaAndNombre(String categoria, String nombre, Integer id) {
        CatalogoModel byCategoriaAndNombre = repository.findByCategoriaAndNombre(categoria, nombre).orElse(null);
        if (byCategoriaAndNombre != null && !byCategoriaAndNombre.getId().equals(id)) {
            if (byCategoriaAndNombre.getEliminado()) {
                throw new AlreadyExistsException(Messages.ALREADY_EXISTS_CATALOGO_BY_NOMBRE_DEACTIVATE);
            }
            throw new AlreadyExistsException(Messages.ALREADY_EXISTS_CATALOGO_BY_NOMBRE);
        }
    }

    private Integer getLastCodigoByCategoria(String categoria) {
        Integer lastCodigo = repository.getLastCodigoByCategoria(categoria);
        if (lastCodigo == null) {
            lastCodigo = 1;
        } else {
            lastCodigo += 1;
        }
        return lastCodigo;
    }

    private void checkParameters(CatalogoRequest request, Integer idCatalogo) {
        Boolean existsByCategoriaAndOrden = repository.existsByCategoriaAndOrden(request.getCategoria(), request.getOrden());
        Integer orden = repository.getLastOrdenByCategoria(request.getCategoria());

        if (existsByCategoriaAndOrden) {
            request.setOrden(orden == null ? 1 : orden + 1);
        }
        checkExistsByCategoriaAndNombre(request.getCategoria(), request.getNombre(), idCatalogo);
    }
}