package tdunu.MsAsistencia.service.Imp;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsAsistencia.model.entity.HistorialAsistenciaModel;
import tdunu.MsAsistencia.model.response.HistorialAsistenciaResponse;
import tdunu.MsAsistencia.repository.HistorialAsistenciaRepository;
import tdunu.MsAsistencia.service.HistorialAsistenciaService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class HistorialAsistenciaServiceImp implements HistorialAsistenciaService {

    @Autowired
    private HistorialAsistenciaRepository historialAsistenciaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<HistorialAsistenciaResponse> listar() {
        log.info("Listando todo el historial de asistencias");
        return historialAsistenciaRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, HistorialAsistenciaResponse.class))
                .toList();
    }

    @Override
    public HistorialAsistenciaResponse obtenerPorId(Integer id) {
        log.info("Obteniendo historial por ID: {}", id);
        return historialAsistenciaRepository.findById(id)
                .map(model -> modelMapper.map(model, HistorialAsistenciaResponse.class))
                .orElse(null);
    }

    @Override
    public List<HistorialAsistenciaResponse> buscarPorDetalle(Integer detalleAsistenciaId) {
        log.info("Buscando historial por detalle de asistencia: {}", detalleAsistenciaId);
        return historialAsistenciaRepository.findByDetalleAsistenciaId(detalleAsistenciaId)
                .stream()
                .map(model -> modelMapper.map(model, HistorialAsistenciaResponse.class))
                .toList();
    }

    @Override
    public void registrarCambio(Integer detalleAsistenciaId, String estadoAnterior, String estadoNuevo, String motivo, Integer usuarioCreacion) {
        log.info("Registrando cambio de estado: {} -> {} para detalle: {}", estadoAnterior, estadoNuevo, detalleAsistenciaId);
        
        HistorialAsistenciaModel model = new HistorialAsistenciaModel();
        model.setDetalleAsistenciaId(detalleAsistenciaId);
        model.setEstadoAnterior(estadoAnterior);
        model.setEstadoNuevo(estadoNuevo);
        model.setMotivoCambio(motivo);
        model.setFechaCreacion(LocalDateTime.now());
        model.setUsuarioCreacion(usuarioCreacion);
        
        historialAsistenciaRepository.save(model);
    }
}
