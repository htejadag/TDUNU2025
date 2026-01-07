package tdunu.MsAsistencia.service.Imp;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsAsistencia.model.entity.DetalleAsistenciaModel;
import tdunu.MsAsistencia.model.request.DetalleAsistenciaRequest;
import tdunu.MsAsistencia.model.request.DetalleAsistenciaMasivoRequest;
import tdunu.MsAsistencia.model.response.DetalleAsistenciaResponse;
import tdunu.MsAsistencia.repository.DetalleAsistenciaRepository;
import tdunu.MsAsistencia.service.DetalleAsistenciaService;
import tdunu.MsAsistencia.service.HistorialAsistenciaService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DetalleAsistenciaServiceImp implements DetalleAsistenciaService {

    @Autowired
    private DetalleAsistenciaRepository detalleAsistenciaRepository;

    @Autowired
    private HistorialAsistenciaService historialAsistenciaService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DetalleAsistenciaResponse> listar() {
        log.info("Listando todos los detalles de asistencia");
        return detalleAsistenciaRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, DetalleAsistenciaResponse.class))
                .toList();
    }

    @Override
    public DetalleAsistenciaResponse obtenerPorId(Integer id) {
        log.info("Obteniendo detalle de asistencia por ID: {}", id);
        return detalleAsistenciaRepository.findById(id)
                .map(model -> modelMapper.map(model, DetalleAsistenciaResponse.class))
                .orElse(null);
    }

    @Override
    public DetalleAsistenciaResponse guardar(DetalleAsistenciaRequest request) {
        log.info("Guardando detalle de asistencia para entidad: {} - {}", request.getTipoEntidad(), request.getEntidadId());
        
        DetalleAsistenciaModel model = new DetalleAsistenciaModel();
        model.setAsistenciaId(request.getAsistenciaId());
        model.setEntidadId(request.getEntidadId());
        model.setTipoEntidad(request.getTipoEntidad());
        model.setEstado(request.getEstado());
        model.setObservaciones(request.getObservaciones());
        model.setFechaCreacion(LocalDateTime.now());
        model.setUsuarioCreacion(request.getUsuarioCreacion());
        
        DetalleAsistenciaModel saved = detalleAsistenciaRepository.save(model);
        return modelMapper.map(saved, DetalleAsistenciaResponse.class);
    }

    @Override
    public DetalleAsistenciaResponse actualizar(Integer id, DetalleAsistenciaRequest request, Integer usuarioModificacion) {
        log.info("Actualizando detalle de asistencia ID: {}", id);
        
        return detalleAsistenciaRepository.findById(id)
                .map(model -> {
                    String estadoAnterior = model.getEstado();
                    
                    model.setAsistenciaId(request.getAsistenciaId());
                    model.setEntidadId(request.getEntidadId());
                    model.setTipoEntidad(request.getTipoEntidad());
                    model.setEstado(request.getEstado());
                    model.setObservaciones(request.getObservaciones());
                    model.setFechaModificacion(LocalDateTime.now());
                    model.setUsuarioModificacion(usuarioModificacion);
                    
                    DetalleAsistenciaModel saved = detalleAsistenciaRepository.save(model);
                    
                    // Registrar en historial si cambió el estado
                    if (!estadoAnterior.equals(request.getEstado())) {
                        historialAsistenciaService.registrarCambio(
                                id, 
                                estadoAnterior, 
                                request.getEstado(), 
                                "Actualización de estado",
                                usuarioModificacion
                        );
                    }
                    
                    return modelMapper.map(saved, DetalleAsistenciaResponse.class);
                })
                .orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando detalle de asistencia ID: {}", id);
        detalleAsistenciaRepository.deleteById(id);
    }

    @Override
    public List<DetalleAsistenciaResponse> buscarPorAsistencia(Integer asistenciaId) {
        log.info("Buscando detalles por asistencia: {}", asistenciaId);
        return detalleAsistenciaRepository.findByAsistenciaId(asistenciaId)
                .stream()
                .map(model -> modelMapper.map(model, DetalleAsistenciaResponse.class))
                .toList();
    }

    @Override
    public List<DetalleAsistenciaResponse> buscarPorEntidad(String tipoEntidad, Integer entidadId) {
        log.info("Buscando detalles por entidad: {} - {}", tipoEntidad, entidadId);
        return detalleAsistenciaRepository.findByTipoEntidadAndEntidadId(tipoEntidad, entidadId)
                .stream()
                .map(model -> modelMapper.map(model, DetalleAsistenciaResponse.class))
                .toList();
    }

    @Override
    public List<DetalleAsistenciaResponse> registrarMasivo(DetalleAsistenciaMasivoRequest request) {
        log.info("Registrando asistencia masiva para asistencia: {}", request.getAsistenciaId());
        
        List<DetalleAsistenciaResponse> responses = new ArrayList<>();
        
        for (DetalleAsistenciaMasivoRequest.DetalleIndividualRequest detalle : request.getDetalles()) {
            DetalleAsistenciaModel model = new DetalleAsistenciaModel();
            model.setAsistenciaId(request.getAsistenciaId());
            model.setEntidadId(detalle.getEntidadId());
            model.setTipoEntidad(detalle.getTipoEntidad());
            model.setEstado(detalle.getEstado());
            model.setObservaciones(detalle.getObservaciones());
            model.setFechaCreacion(LocalDateTime.now());
            model.setUsuarioCreacion(request.getUsuarioCreacion());
            
            DetalleAsistenciaModel saved = detalleAsistenciaRepository.save(model);
            responses.add(modelMapper.map(saved, DetalleAsistenciaResponse.class));
        }
        
        return responses;
    }
}
