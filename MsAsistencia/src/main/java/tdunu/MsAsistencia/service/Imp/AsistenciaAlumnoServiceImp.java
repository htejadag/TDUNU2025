package tdunu.MsAsistencia.service.Imp;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsAsistencia.model.entity.AsistenciaAlumnoModel;
import tdunu.MsAsistencia.model.request.AsistenciaAlumnoRequest;
import tdunu.MsAsistencia.model.response.AsistenciaAlumnoResponse;
import tdunu.MsAsistencia.repository.AsistenciaAlumnoRepository;
import tdunu.MsAsistencia.service.AsistenciaAlumnoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AsistenciaAlumnoServiceImp implements AsistenciaAlumnoService {

    @Autowired
    private AsistenciaAlumnoRepository asistenciaAlumnoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AsistenciaAlumnoResponse> listar() {
        log.info("Listando todas las asistencias de alumnos");
        return asistenciaAlumnoRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, AsistenciaAlumnoResponse.class))
                .toList();
    }

    @Override
    public AsistenciaAlumnoResponse obtenerPorId(Integer id) {
        log.info("Obteniendo asistencia de alumno por ID: {}", id);
        return asistenciaAlumnoRepository.findById(id)
                .map(model -> modelMapper.map(model, AsistenciaAlumnoResponse.class))
                .orElse(null);
    }

    @Override
    public AsistenciaAlumnoResponse guardar(AsistenciaAlumnoRequest request) {
        log.info("Guardando asistencia para alumno: {}", request.getAlumnoId());

        // Mapeo manual para evitar conflictos
        AsistenciaAlumnoModel model = new AsistenciaAlumnoModel();
        model.setAlumnoId(request.getAlumnoId());
        model.setCursoId(request.getCursoId());
        model.setSeccionId(request.getSeccionId());
        model.setEstado(request.getEstado());
        model.setJustificacion(request.getJustificacion());
        model.setObservaciones(request.getObservaciones());
        model.setUsuarioRegistro(request.getUsuarioRegistro());

        // Fechas automáticas
        LocalDateTime ahora = LocalDateTime.now();
        model.setFecha(request.getFecha() != null ? request.getFecha() : LocalDate.now());
        model.setHoraEntrada(request.getHoraEntrada() != null ? request.getHoraEntrada() : ahora);
        model.setHoraSalida(request.getHoraSalida());
        model.setFechaRegistro(ahora);

        AsistenciaAlumnoModel saved = asistenciaAlumnoRepository.save(model);

        return modelMapper.map(saved, AsistenciaAlumnoResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando asistencia de alumno con ID: {}", id);
        asistenciaAlumnoRepository.deleteById(id);
    }

    @Override
    public List<AsistenciaAlumnoResponse> buscarPorAlumno(Integer alumnoId) {
        log.info("Buscando asistencias del alumno: {}", alumnoId);
        return asistenciaAlumnoRepository.findByAlumnoId(alumnoId)
                .stream()
                .map(model -> modelMapper.map(model, AsistenciaAlumnoResponse.class))
                .toList();
    }
}
