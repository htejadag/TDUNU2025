package com.example.Comedor.service.Imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.Comedor.config.BusinessException;
import com.example.Comedor.model.entity.CatalogoModel;
import com.example.Comedor.model.entity.PlatoModel;
import com.example.Comedor.model.request.plato.PlatoRequest;
import com.example.Comedor.model.request.plato.PlatoUpdateRequest;
import com.example.Comedor.model.response.PlatoResponse;
import com.example.Comedor.repository.CatalogoRepository;
import com.example.Comedor.repository.PlatoRepository;
import com.example.Comedor.service.PlatoService;
import com.example.Comedor.util.CatalogoEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlatoServiceImp implements PlatoService {

    private final PlatoRepository platoRepository;

    private final ModelMapper modelMapper;

    private final CatalogoRepository catalogoRepository;

    public PlatoServiceImp(PlatoRepository platoRepository,
            ModelMapper modelMapper,
            CatalogoRepository catalogoRepository) {

        this.platoRepository = platoRepository;
        this.modelMapper = modelMapper;
        this.catalogoRepository = catalogoRepository;
    }

    @Override
    public List<PlatoResponse> listar() {

        return platoRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, PlatoResponse.class))
                .toList();

    }

    @Override
    public PlatoResponse obtenerPorId(Integer id) {
        return platoRepository.findById(id)
                .map(model -> modelMapper.map(model, PlatoResponse.class))
                .orElse(null);

    }

    @Override
    public PlatoResponse guardar(PlatoRequest platoRequest) {

        PlatoModel model = new PlatoModel();

        Integer idTipoReq = platoRequest.getIdTipo();

        if (!idTipoReq.equals(CatalogoEnum.DESAYUNO.getId()) &&
                !idTipoReq.equals(CatalogoEnum.ALMUERZO.getId()) &&
                !idTipoReq.equals(CatalogoEnum.CENA.getId())) {
            throw new BusinessException("tipo de plato no válido");
        }

        CatalogoModel idTipo = catalogoRepository.findById(idTipoReq)
                .orElseThrow(() -> new RuntimeException("No existe el tipo de plato"));

        model.setNombre(platoRequest.getNombre());
        model.setDescripcion(platoRequest.getDescripcion());
        model.setImagenUrl(platoRequest.getImagenUrl());
        model.setCalorias(platoRequest.getCalorias());
        model.setIdTipo(idTipo);
        model.setActivo(platoRequest.isActivo());
        model.setUsuarioCreacion(platoRequest.getUsuarioCreacion());
        model.setFechaCreacion(LocalDate.now());

        PlatoModel saved = platoRepository.save(model);

        return modelMapper.map(saved, PlatoResponse.class);

    }

    @Override
    public PlatoResponse modificar(Integer id, PlatoUpdateRequest platoRequest) {
        PlatoModel model = platoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un plato con id: " + id));

        Integer idTipoReq = platoRequest.getIdTipo();

        if (!idTipoReq.equals(CatalogoEnum.DESAYUNO.getId()) &&
                !idTipoReq.equals(CatalogoEnum.ALMUERZO.getId()) &&
                !idTipoReq.equals(CatalogoEnum.CENA.getId())) {
            throw new BusinessException("tipo de plato no válido");
        }

        CatalogoModel idTipo = catalogoRepository.findById(idTipoReq)
                .orElseThrow(() -> new RuntimeException("No existe el tipo de plato"));

        modelMapper.map(platoRequest, model);

        model.setNombre(platoRequest.getNombre());
        model.setDescripcion(platoRequest.getDescripcion());
        model.setImagenUrl(platoRequest.getImagenUrl());
        model.setCalorias(platoRequest.getCalorias());
        model.setIdTipo(idTipo);
        model.setActivo(platoRequest.isActivo());
        model.setUsuarioModificacion(platoRequest.getUsuarioModificacion());
        model.setFechaModificacion(LocalDate.now());

        PlatoModel actualizado = platoRepository.save(model);

        return modelMapper.map(actualizado, PlatoResponse.class);
    }

    @Override
    public PlatoResponse eliminar(Integer id) {
        PlatoModel model = platoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un plato con id: " + id));

        model.setActivo(false);

        PlatoModel actualizado = platoRepository.save(model);

        return modelMapper.map(actualizado, PlatoResponse.class);

    }

}
