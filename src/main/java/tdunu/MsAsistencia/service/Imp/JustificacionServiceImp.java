package tdunu.MsAsistencia.service.Imp;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsAsistencia.model.entity.DetalleAsistenciaModel;
import tdunu.MsAsistencia.model.entity.JustificacionModel;
import tdunu.MsAsistencia.model.request.JustificacionRequest;
import tdunu.MsAsistencia.model.response.JustificacionResponse;
import tdunu.MsAsistencia.repository.DetalleAsistenciaRepository;
import tdunu.MsAsistencia.repository.JustificacionRepository;
import tdunu.MsAsistencia.service.HistorialAsistenciaService;
import tdunu.MsAsistencia.service.JustificacionService;
import tdunu.MsAsistencia.util.Mensajes;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class JustificacionServiceImp implements JustificacionService {

    @Autowired
    private JustificacionRepository justificacionRepository;

    @Autowired
    private DetalleAsistenciaRepository detalleAsistenciaRepository;

    @Autowired
    private HistorialAsistenciaService historialAsistenciaService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<JustificacionResponse> listar() {
        log.info("Listando todas las justificaciones");
        return justificacionRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, JustificacionResponse.class))
                .toList();
    }

    @Override
    public JustificacionResponse obtenerPorId(Integer id) {
        log.info("Obteniendo justificación por ID: {}", id);
        return justificacionRepository.findById(id)
                .map(model -> modelMapper.map(model, JustificacionResponse.class))
                .orElse(null);
    }

    @Override
    public JustificacionResponse guardar(JustificacionRequest request) {
        log.info("Guardando nueva justificación para detalle: {}", request.getDetalleAsistenciaId());
        
        JustificacionModel model = new JustificacionModel();
        model.setDetalleAsistenciaId(request.getDetalleAsistenciaId());
        model.setMotivo(request.getMotivo());
        model.setTipoDocumento(request.getTipoDocumento());
        model.setNumeroDocumento(request.getNumeroDocumento());
        model.setArchivoAdjunto(request.getArchivoAdjunto());
        model.setFechaJustificacion(request.getFechaJustificacion());
        model.setAprobado("PENDIENTE");
        model.setFechaCreacion(LocalDateTime.now());
        model.setUsuarioCreacion(request.getUsuarioCreacion());
        
        JustificacionModel saved = justificacionRepository.save(model);
        return modelMapper.map(saved, JustificacionResponse.class);
    }

    @Override
    public JustificacionResponse actualizar(Integer id, JustificacionRequest request) {
        log.info("Actualizando justificación ID: {}", id);
        
        return justificacionRepository.findById(id)
                .map(model -> {
                    model.setMotivo(request.getMotivo());
                    model.setTipoDocumento(request.getTipoDocumento());
                    model.setNumeroDocumento(request.getNumeroDocumento());
                    model.setArchivoAdjunto(request.getArchivoAdjunto());
                    model.setFechaJustificacion(request.getFechaJustificacion());
                    model.setFechaModificacion(LocalDateTime.now());
                    model.setUsuarioModificacion(request.getUsuarioCreacion());
                    
                    JustificacionModel saved = justificacionRepository.save(model);
                    return modelMapper.map(saved, JustificacionResponse.class);
                })
                .orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando justificación ID: {}", id);
        justificacionRepository.deleteById(id);
    }

    @Override
    public JustificacionResponse aprobar(Integer id, Integer usuarioModificacion) {
        log.info("Aprobando justificación ID: {}", id);
        
        return justificacionRepository.findById(id)
                .map(model -> {
                    model.setAprobado("SI");
                    model.setFechaModificacion(LocalDateTime.now());
                    model.setUsuarioModificacion(usuarioModificacion);
                    
                    JustificacionModel saved = justificacionRepository.save(model);
                    
                    // Actualizar el estado del detalle de asistencia a JUSTIFICADO
                    detalleAsistenciaRepository.findById(model.getDetalleAsistenciaId())
                            .ifPresent(detalle -> {
                                String estadoAnterior = detalle.getEstado();
                                detalle.setEstado(Mensajes.ESTADO_JUSTIFICADO);
                                detalle.setFechaModificacion(LocalDateTime.now());
                                detalle.setUsuarioModificacion(usuarioModificacion);
                                detalleAsistenciaRepository.save(detalle);
                                
                                // Registrar en historial
                                historialAsistenciaService.registrarCambio(
                                        detalle.getId(),
                                        estadoAnterior,
                                        Mensajes.ESTADO_JUSTIFICADO,
                                        "Justificación aprobada",
                                        usuarioModificacion
                                );
                            });
                    
                    return modelMapper.map(saved, JustificacionResponse.class);
                })
                .orElse(null);
    }

    @Override
    public JustificacionResponse rechazar(Integer id, Integer usuarioModificacion) {
        log.info("Rechazando justificación ID: {}", id);
        
        return justificacionRepository.findById(id)
                .map(model -> {
                    model.setAprobado("NO");
                    model.setFechaModificacion(LocalDateTime.now());
                    model.setUsuarioModificacion(usuarioModificacion);
                    
                    JustificacionModel saved = justificacionRepository.save(model);
                    return modelMapper.map(saved, JustificacionResponse.class);
                })
                .orElse(null);
    }

    @Override
    public List<JustificacionResponse> buscarPendientes() {
        log.info("Buscando justificaciones pendientes");
        return justificacionRepository.findByAprobado("PENDIENTE")
                .stream()
                .map(model -> modelMapper.map(model, JustificacionResponse.class))
                .toList();
    }
}
