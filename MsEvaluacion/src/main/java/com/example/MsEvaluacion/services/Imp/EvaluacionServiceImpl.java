package com.example.MsEvaluacion.services.Imp;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsEvaluacion.message.EvaluacionFinalPublisher;
import com.example.MsEvaluacion.model.entity.CursoModel;
import com.example.MsEvaluacion.model.entity.EvaluacionDetalleModel;
import com.example.MsEvaluacion.model.entity.EvaluacionModel;
import com.example.MsEvaluacion.model.events.EvaluacionFinalRegistradaEvent;
import com.example.MsEvaluacion.model.request.EvaluacionRequest;
import com.example.MsEvaluacion.model.response.EvaluacionResponse;
import com.example.MsEvaluacion.repository.IEvaluacionRepository;
import com.example.MsEvaluacion.repository.cursoRepository;
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

    @Autowired
    cursoRepository cursoRepo;

    @Autowired
    private EvaluacionFinalPublisher evaluacionFinalPublisher;

    @Override
    public void eliminar(String id) {

    }

    @Override
    public EvaluacionResponse guardar(EvaluacionRequest evaluacionRequest) {

        Integer idCursoDetalle = Integer.parseInt(evaluacionRequest.getIdCursoDetalle());

        CursoModel cursoExistente = cursoRepo.findByIdDetalleCurso(idCursoDetalle);

        if (cursoExistente == null) {
            throw new RuntimeException("No se puede crear la evaluación: El curso con el ID "
                    + evaluacionRequest.getIdCursoDetalle() + " no esta registrado");
        }

        EvaluacionModel evaluacionModel = modelMapper.map(evaluacionRequest, EvaluacionModel.class);

        evaluacionModel.setPromedioFinal(null);
        evaluacionModel.setFechaEvaluacion(java.time.LocalDateTime.now());

        EvaluacionModel saved = evaluacionRepository.save(evaluacionModel);

        return modelMapper.map(saved, EvaluacionResponse.class);
    }

    @Override
    public EvaluacionResponse actualizar(String id, EvaluacionRequest evaluacionRequest) {
        EvaluacionModel existente = evaluacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID: " + id));

        // Validar que el curso existe si se está cambiando
        if (evaluacionRequest.getIdCursoDetalle() != null) {
            Integer idCursoDetalle = Integer.parseInt(evaluacionRequest.getIdCursoDetalle());
            CursoModel cursoExistente = cursoRepo.findByIdDetalleCurso(idCursoDetalle);
            if (cursoExistente == null) {
                throw new RuntimeException("No se puede actualizar: El curso con ID "
                        + evaluacionRequest.getIdCursoDetalle() + " no está registrado");
            }
            existente.setIdCursoDetalle(evaluacionRequest.getIdCursoDetalle());
        }

        if (evaluacionRequest.getIdAlumno() != null) {
            existente.setIdAlumno(evaluacionRequest.getIdAlumno());
        }
        if (evaluacionRequest.getIdCicloAcademico() != null) {
            existente.setIdCicloAcademico(evaluacionRequest.getIdCicloAcademico());
        }

        EvaluacionModel saved = evaluacionRepository.save(existente);
        return modelMapper.map(saved, EvaluacionResponse.class);
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

    @Override
    public void calcularPromedioFinal(String evaluacionId) {

        EvaluacionModel evaluacion = evaluacionRepository.findById(evaluacionId)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));

        var detalles = eDetalleRepository.findByEvaluacion(evaluacionId);

        if (detalles == null || detalles.isEmpty())
            return;

        Double promedioPracticas = detalles.stream()
                .filter(d -> "PC".equals(d.getCatalogoid()))
                .map(EvaluacionDetalleModel::getPromedio)
                .findFirst()
                .orElse(null);

        Double notaParcial = detalles.stream()
                .filter(d -> "EP".equals(d.getCatalogoid()))
                .map(EvaluacionDetalleModel::getPromedio)
                .findFirst()
                .orElse(null);

        Double notaFinal = detalles.stream()
                .filter(d -> "EF".equals(d.getCatalogoid()))
                .map(EvaluacionDetalleModel::getPromedio)
                .findFirst()
                .orElse(null);

        Double promedioFinal = EvaluacionCalculador.calcularPromedioFinal(
                promedioPracticas, notaParcial, notaFinal);

        if (promedioFinal == null)
            return;

        evaluacion.setPromedioFinal(promedioFinal);
        evaluacion.setFechaEvaluacion(java.time.LocalDateTime.now());

        evaluacionRepository.save(evaluacion);

        String idCursoDetalle = evaluacion.getIdCursoDetalle();

        long total = evaluacionRepository.countByIdCursoDetalle(idCursoDetalle);
        long finalizados = evaluacionRepository
                .countByIdCursoDetalleAndPromedioFinalNotNull(idCursoDetalle);

        if (total > 0 && total == finalizados) {

            EvaluacionFinalRegistradaEvent ev = new EvaluacionFinalRegistradaEvent();
            ev.setEventId(UUID.randomUUID().toString());
            ev.setAction("CURSO_COMPLETADO");
            ev.setIdCursoDetalle(Integer.valueOf(idCursoDetalle));
            ev.setTimestamp(System.currentTimeMillis());

            evaluacionFinalPublisher.publish(ev);

            log.info("Evento CURSO_COMPLETADO enviado a Kafka: {}", ev);
        }
    }

}
