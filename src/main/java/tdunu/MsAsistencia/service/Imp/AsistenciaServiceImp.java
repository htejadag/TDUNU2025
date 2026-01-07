package tdunu.MsAsistencia.service.Imp;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsAsistencia.model.entity.AsistenciaModel;
import tdunu.MsAsistencia.model.request.AsistenciaRequest;
import tdunu.MsAsistencia.model.response.AsistenciaResponse;
import tdunu.MsAsistencia.repository.AsistenciaRepository;
import tdunu.MsAsistencia.service.AsistenciaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AsistenciaServiceImp implements AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AsistenciaResponse> listar() {
        log.info("Listando todas las asistencias");
        return asistenciaRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, AsistenciaResponse.class))
                .toList();
    }

    @Override
    public AsistenciaResponse obtenerPorId(Integer id) {
        log.info("Obteniendo asistencia por ID: {}", id);
        return asistenciaRepository.findById(id)
                .map(model -> modelMapper.map(model, AsistenciaResponse.class))
                .orElse(null);
    }

    @Override
    public AsistenciaResponse guardar(AsistenciaRequest request) {
        log.info("Guardando nueva asistencia para programación: {}", request.getProgramacionId());
        
        AsistenciaModel model = new AsistenciaModel();
        model.setProgramacionId(request.getProgramacionId());
        model.setNumeroSesion(request.getNumeroSesion());
        model.setFecha(request.getFecha());
        model.setHoraInicio(request.getHoraInicio());
        model.setHoraFin(request.getHoraFin());
        model.setSedeId(request.getSedeId());
        model.setAulaId(request.getAulaId());
        model.setTemaTratado(request.getTemaTratado());
        model.setEstado(request.getEstado());
        model.setObservaciones(request.getObservaciones());
        model.setFechaCreacion(LocalDateTime.now());
        model.setUsuarioCreacion(request.getUsuarioCreacion());
        
        AsistenciaModel saved = asistenciaRepository.save(model);
        return modelMapper.map(saved, AsistenciaResponse.class);
    }

    @Override
    public AsistenciaResponse actualizar(Integer id, AsistenciaRequest request) {
        log.info("Actualizando asistencia ID: {}", id);
        
        return asistenciaRepository.findById(id)
                .map(model -> {
                    model.setProgramacionId(request.getProgramacionId());
                    model.setNumeroSesion(request.getNumeroSesion());
                    model.setFecha(request.getFecha());
                    model.setHoraInicio(request.getHoraInicio());
                    model.setHoraFin(request.getHoraFin());
                    model.setSedeId(request.getSedeId());
                    model.setAulaId(request.getAulaId());
                    model.setTemaTratado(request.getTemaTratado());
                    model.setEstado(request.getEstado());
                    model.setObservaciones(request.getObservaciones());
                    model.setFechaModificacion(LocalDateTime.now());
                    model.setUsuarioModificacion(request.getUsuarioCreacion());
                    
                    AsistenciaModel saved = asistenciaRepository.save(model);
                    return modelMapper.map(saved, AsistenciaResponse.class);
                })
                .orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando asistencia ID: {}", id);
        asistenciaRepository.deleteById(id);
    }

    @Override
    public List<AsistenciaResponse> buscarPorProgramacion(Integer programacionId) {
        log.info("Buscando asistencias por programación: {}", programacionId);
        return asistenciaRepository.findByProgramacionId(programacionId)
                .stream()
                .map(model -> modelMapper.map(model, AsistenciaResponse.class))
                .toList();
    }

    @Override
    public List<AsistenciaResponse> buscarPorFecha(LocalDate fecha) {
        log.info("Buscando asistencias por fecha: {}", fecha);
        return asistenciaRepository.findByFecha(fecha)
                .stream()
                .map(model -> modelMapper.map(model, AsistenciaResponse.class))
                .toList();
    }
}
