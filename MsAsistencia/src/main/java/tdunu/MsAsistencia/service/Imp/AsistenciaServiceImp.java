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
        log.info("Guardando nueva asistencia para entidad: {} - {}", request.getTipoEntidad(), request.getEntidadId());
        
        AsistenciaModel model = modelMapper.map(request, AsistenciaModel.class);
        model.setFechaRegistro(LocalDateTime.now());
        
        AsistenciaModel saved = asistenciaRepository.save(model);
        
        return modelMapper.map(saved, AsistenciaResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando asistencia con ID: {}", id);
        asistenciaRepository.deleteById(id);
    }

    @Override
    public List<AsistenciaResponse> buscarPorEntidad(String tipoEntidad, Integer entidadId) {
        log.info("Buscando asistencias por entidad: {} - {}", tipoEntidad, entidadId);
        return asistenciaRepository.findByTipoEntidadAndEntidadId(tipoEntidad, entidadId)
                .stream()
                .map(model -> modelMapper.map(model, AsistenciaResponse.class))
                .toList();
    }

    @Override
    public List<AsistenciaResponse> buscarPorEvento(String tipoEvento, Integer eventoId) {
        log.info("Buscando asistencias por evento: {} - {}", tipoEvento, eventoId);
        return asistenciaRepository.findByTipoEventoAndEventoId(tipoEvento, eventoId)
                .stream()
                .map(model -> modelMapper.map(model, AsistenciaResponse.class))
                .toList();
    }

    @Override
    public List<AsistenciaResponse> buscarPorFecha(LocalDateTime inicio, LocalDateTime fin) {
        log.info("Buscando asistencias entre: {} y {}", inicio, fin);
        return asistenciaRepository.findByFechaHoraBetween(inicio, fin)
                .stream()
                .map(model -> modelMapper.map(model, AsistenciaResponse.class))
                .toList();
    }
}
