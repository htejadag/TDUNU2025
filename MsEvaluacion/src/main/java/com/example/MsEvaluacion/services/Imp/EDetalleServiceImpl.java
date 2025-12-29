package com.example.MsEvaluacion.services.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsEvaluacion.model.entity.EvaluacionDetalleModel;
import com.example.MsEvaluacion.model.request.EDetalleResquest;
import com.example.MsEvaluacion.model.response.EDetalleResponse;
import com.example.MsEvaluacion.repository.IEDetalleRepository;
import com.example.MsEvaluacion.services.IEDetalleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EDetalleServiceImpl implements IEDetalleService {

    @Autowired
    IEDetalleRepository eDetalleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void eliminar(String id) {

    }

    @Override
    public EDetalleResponse guardar(EDetalleResquest request) {

        validarTipoEvaluacion(request.getCatalogoid());
        validarNumero(request.getCatalogoid(), request.getNumero());
        validarDuplicado(
                request.getEvaluacion(),
                request.getCatalogoid(),
                request.getNumero());

        EvaluacionDetalleModel entity = modelMapper.map(request, EvaluacionDetalleModel.class);

        EvaluacionDetalleModel saved = eDetalleRepository.save(entity);

        return modelMapper.map(saved, EDetalleResponse.class);
    }

    private void validarTipoEvaluacion(String catalogoid) {
        if (!List.of("PC", "EP", "EF").contains(catalogoid)) {
            throw new RuntimeException("Tipo de evaluación no válido");
        }
    }

    private void validarNumero(String catalogoid, Integer numero) {
        if ("PC".equals(catalogoid) && numero == null) {
            throw new RuntimeException("La práctica requiere número");
        }

        if (!"PC".equals(catalogoid) && numero != null) {
            throw new RuntimeException("Solo las prácticas llevan número");
        }
    }

    private void validarDuplicado(
            String evaluacion,
            String catalogoid,
            Integer numero) {

        boolean existe = eDetalleRepository
                .existsByEvaluacionAndCatalogoidAndNumero(
                        evaluacion, catalogoid, numero);

        if (existe) {
            throw new RuntimeException("Ya existe esta evaluación");
        }
    }

    @Override
    public List<EvaluacionDetalleModel> listarPorEvaluacion(String evaluacion) {
        return eDetalleRepository.findByEvaluacion(evaluacion);
    }

    @Override
    public List<EDetalleResponse> listar() {
        return eDetalleRepository.findAll()
                .stream()
                .map(evaluaciond -> modelMapper.map(evaluaciond, EDetalleResponse.class))
                .toList();
    }

    @Override
    public EDetalleResponse obtenerPorId(String id) {
        return eDetalleRepository.findById(id)
                .map(model -> modelMapper.map(model, EDetalleResponse.class))
                .orElse(null);
    }

}
