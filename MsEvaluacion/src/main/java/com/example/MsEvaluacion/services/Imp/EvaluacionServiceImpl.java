package com.example.MsEvaluacion.services.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsEvaluacion.model.entity.EvaluacionModel;
import com.example.MsEvaluacion.model.request.EvaluacionRequest;
import com.example.MsEvaluacion.model.response.EvaluacionResponse;
import com.example.MsEvaluacion.repository.IEvaluacionRepository;
import com.example.MsEvaluacion.repository.IEDetalleRepository;
import com.example.MsEvaluacion.services.IEvaluacionService;
import com.example.MsEvaluacion.util.EvaluacionCalculador;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EvaluacionServiceImpl implements IEvaluacionService {

    @Autowired
    IEvaluacionRepository evaluacionRepository;

    @Autowired
    IEDetalleRepository eDetalleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void eliminar(String id) {
        

    }

    @Override
    public EvaluacionResponse guardar(EvaluacionRequest evaluacionRequest) {

        EvaluacionModel evaluacionModel = modelMapper.map(evaluacionRequest, EvaluacionModel.class);

        var detalles = eDetalleRepository.findByEvaluacion(evaluacionModel.getId());

        if (detalles == null || detalles.isEmpty()) {
            throw new RuntimeException("No hay detalles de evaluación para calcular el promedio");
        }

        var promedioPracticas = detalles.stream()
                .filter(d -> "PC".equals(d.getCatalogoid()))
                .map(d -> d.getPromedio())
                .findFirst()
                .orElse(null);

        var notaParcial = detalles.stream()
                .filter(d -> "EP".equals(d.getCatalogoid()))
                .map(d -> d.getPromedio())
                .findFirst()
                .orElse(null);

        var notaFinal = detalles.stream()
                .filter(d -> "EF".equals(d.getCatalogoid()))
                .map(d -> d.getPromedio())
                .findFirst()
                .orElse(null);

        Double promedioFinal = EvaluacionCalculador.calcularPromedioFinal(promedioPracticas, notaParcial, notaFinal);

        if (promedioFinal == null) {
            throw new RuntimeException("No se pudieron obtener todas las notas para calcular el promedio final");
        }

        evaluacionModel.setPromedioFinal(promedioFinal);

        EvaluacionModel saved = evaluacionRepository.save(evaluacionModel);
        EvaluacionResponse response = modelMapper.map(saved, EvaluacionResponse.class);
        return response;

    }

    @Override
    public List<EvaluacionResponse> listar() {
        return evaluacionRepository.findAll()
                .stream()
                .map(evaluacion -> modelMapper.map(evaluacion, EvaluacionResponse.class))
                .toList();
    }

    @Override
    public EvaluacionResponse obtenerPorId(String id) {
        return evaluacionRepository.findById(id)
                .map(model -> modelMapper.map(model, EvaluacionResponse.class))
                .orElse(null);
    }

}
