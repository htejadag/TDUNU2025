package com.example.MsEvaluacion.services.Imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsEvaluacion.model.entity.EvaluacionDetalleModel;
import com.example.MsEvaluacion.model.request.EDetalleResquest;
import com.example.MsEvaluacion.model.response.CatalogoResponse;
import com.example.MsEvaluacion.model.response.EDetalleResponse;
import com.example.MsEvaluacion.repository.IEDetalleRepository;
import com.example.MsEvaluacion.services.ICatalogoService;
import com.example.MsEvaluacion.services.IEDetalleService;
import com.example.MsEvaluacion.util.EvaluacionCalculador;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EDetalleServiceImpl implements IEDetalleService {

    @Autowired
    IEDetalleRepository eDetalleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ICatalogoService catalogoService;

    @Override
    public void eliminar(String id) {

    }

    @Override
    public EDetalleResponse guardar(EDetalleResquest request) {

        validarTipoEvaluacion(request.getCatalogoid());
        validarDuplicado(
                request.getEvaluacion(),
                request.getCatalogoid());

        EvaluacionDetalleModel entity = modelMapper.map(request, EvaluacionDetalleModel.class);

        if ("PC".equals(request.getCatalogoid())) {
            if (entity.getNotas() == null || entity.getNotas().isEmpty()) {
                throw new RuntimeException("Las prácticas requieren al menos una nota");
            }
            if (!EvaluacionCalculador.sonNotasValidas(entity.getNotas())) {
                throw new RuntimeException("Una o más notas están fuera del rango 0-20");
            }
            Double promedio = EvaluacionCalculador.calcularPromedioPracticas(entity.getNotas());
            entity.setPromedio(promedio);
        } 
        else if ("EP".equals(request.getCatalogoid()) || "EF".equals(request.getCatalogoid())) {
            if (entity.getNota() == null) {
                throw new RuntimeException("Esta evaluación requiere una nota");
            }
            if (!EvaluacionCalculador.esNotaValida(entity.getNota())) {
                throw new RuntimeException("La nota debe estar entre 0-20");
            }
            entity.setPromedio(entity.getNota());
        }

        EvaluacionDetalleModel saved = eDetalleRepository.save(entity);

        return modelMapper.map(saved, EDetalleResponse.class);
    }

    private void validarTipoEvaluacion(String catalogoid) {
        if (!List.of("PC", "EP", "EF").contains(catalogoid)) {
            throw new RuntimeException("Tipo de evaluación no válido");
        }
    }

    private void validarDuplicado(
            String evaluacion,
            String catalogoid) {

        boolean existe = eDetalleRepository
                .existsByEvaluacionAndCatalogoid(
                        evaluacion, catalogoid);

        if (existe) {
            throw new RuntimeException("Ya existe esta evaluación");
        }
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

    @Override
    public List<EDetalleResponse> listarPorEvaluacion(String evaluacion) {
        List<EvaluacionDetalleModel> listaModelos = eDetalleRepository.findByEvaluacion(evaluacion);

        return listaModelos.stream()
                .map(this::convertirADtoConCatalogo)
                .collect(Collectors.toList());
    }

    private EDetalleResponse convertirADtoConCatalogo(EvaluacionDetalleModel entity) {
        EDetalleResponse response = modelMapper.map(entity, EDetalleResponse.class);
    
        if (entity.getCatalogoid() != null) {
            try {
                CatalogoResponse cat = catalogoService.obtenerCatalogoPorCodigo(entity.getCatalogoid());
                response.setCatalogo(cat); 
            } catch (Exception e) {
                log.warn("No se encontró catálogo: " + entity.getCatalogoid());
            }
        }
        return response;
    }
}
