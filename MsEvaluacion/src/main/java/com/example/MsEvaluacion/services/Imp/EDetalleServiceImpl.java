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
import com.example.MsEvaluacion.repository.IEvaluacionRepository;
import com.example.MsEvaluacion.services.ICatalogoService;
import com.example.MsEvaluacion.services.IEDetalleService;
import com.example.MsEvaluacion.services.IEvaluacionService;
import com.example.MsEvaluacion.util.EvaluacionCalculador;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EDetalleServiceImpl implements IEDetalleService {

    @Autowired
    IEDetalleRepository eDetalleRepository;

    @Autowired
    IEvaluacionRepository evaluacionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ICatalogoService catalogoService;
    @Autowired
    private IEvaluacionService evaluacionService;

    @Override
    public void eliminar(String id) {

    }

    @Override
    public EDetalleResponse guardar(EDetalleResquest request) {

        // Validar que la evaluación existe
        validarEvaluacionExiste(request.getEvaluacion());

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
        } else if ("EP".equals(request.getCatalogoid()) || "EF".equals(request.getCatalogoid())) {
            if (entity.getNota() == null) {
                throw new RuntimeException("Esta evaluación requiere una nota");
            }
            if (!EvaluacionCalculador.esNotaValida(entity.getNota())) {
                throw new RuntimeException("La nota debe estar entre 0-20");
            }
            entity.setPromedio(entity.getNota());
        }

        EvaluacionDetalleModel saved = eDetalleRepository.save(entity);

        if ("EF".equals(saved.getCatalogoid())) {
            evaluacionService.calcularPromedioFinal(saved.getEvaluacion());
        }

        return modelMapper.map(saved, EDetalleResponse.class);
    }

    @Override
    public EDetalleResponse actualizar(String id, EDetalleResquest request) {
        EvaluacionDetalleModel existente = eDetalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de evaluación no encontrado con ID: " + id));

        // Actualizar notas según el tipo de evaluación
        if ("PC".equals(existente.getCatalogoid())) {
            if (request.getNotas() != null && !request.getNotas().isEmpty()) {
                if (!EvaluacionCalculador.sonNotasValidas(request.getNotas())) {
                    throw new RuntimeException("Una o más notas están fuera del rango 0-20");
                }
                existente.setNotas(request.getNotas());
                Double promedio = EvaluacionCalculador.calcularPromedioPracticas(request.getNotas());
                existente.setPromedio(promedio);
            }
        } else if ("EP".equals(existente.getCatalogoid()) || "EF".equals(existente.getCatalogoid())) {
            if (request.getNota() != null) {
                if (!EvaluacionCalculador.esNotaValida(request.getNota())) {
                    throw new RuntimeException("La nota debe estar entre 0-20");
                }
                existente.setNota(request.getNota());
                existente.setPromedio(request.getNota());
            }
        }

        if (request.getFechaPrueba() != null) {
            existente.setFechaPrueba(request.getFechaPrueba());
        }

        EvaluacionDetalleModel saved = eDetalleRepository.save(existente);

        // Recalcular promedio final si se actualizó
        evaluacionService.calcularPromedioFinal(saved.getEvaluacion());

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

    private void validarEvaluacionExiste(String evaluacionId) {
        boolean existe = evaluacionRepository.existsById(evaluacionId);
        if (!existe) {
            throw new RuntimeException("La evaluación con ID " + evaluacionId + " no existe en el sistema");
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
