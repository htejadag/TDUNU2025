package tdunu.MsAsistencia.service.Imp;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsAsistencia.model.entity.ProgramacionModel;
import tdunu.MsAsistencia.model.request.ProgramacionRequest;
import tdunu.MsAsistencia.model.response.ProgramacionResponse;
import tdunu.MsAsistencia.repository.ProgramacionRepository;
import tdunu.MsAsistencia.service.ProgramacionService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ProgramacionServiceImp implements ProgramacionService {

    @Autowired
    private ProgramacionRepository programacionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProgramacionResponse> listar() {
        log.info("Listando todas las programaciones");
        return programacionRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, ProgramacionResponse.class))
                .toList();
    }

    @Override
    public ProgramacionResponse obtenerPorId(Integer id) {
        log.info("Obteniendo programación por ID: {}", id);
        return programacionRepository.findById(id)
                .map(model -> modelMapper.map(model, ProgramacionResponse.class))
                .orElse(null);
    }

    @Override
    public ProgramacionResponse guardar(ProgramacionRequest request) {
        log.info("Guardando nueva programación: {}", request.getNombre());
        
        ProgramacionModel model = new ProgramacionModel();
        model.setSistemaOrigen(request.getSistemaOrigen());
        model.setTipoProgramacion(request.getTipoProgramacion());
        model.setNombre(request.getNombre());
        model.setDescripcion(request.getDescripcion());
        model.setContextoId(request.getContextoId());
        model.setTipoContexto(request.getTipoContexto());
        model.setFechaInicio(request.getFechaInicio());
        model.setFechaFin(request.getFechaFin());
        model.setTotalSesiones(request.getTotalSesiones());
        model.setEstado(request.getEstado());
        model.setFechaCreacion(LocalDateTime.now());
        model.setUsuarioCreacion(request.getUsuarioCreacion());
        
        ProgramacionModel saved = programacionRepository.save(model);
        return modelMapper.map(saved, ProgramacionResponse.class);
    }

    @Override
    public ProgramacionResponse actualizar(Integer id, ProgramacionRequest request) {
        log.info("Actualizando programación ID: {}", id);
        
        return programacionRepository.findById(id)
                .map(model -> {
                    model.setSistemaOrigen(request.getSistemaOrigen());
                    model.setTipoProgramacion(request.getTipoProgramacion());
                    model.setNombre(request.getNombre());
                    model.setDescripcion(request.getDescripcion());
                    model.setContextoId(request.getContextoId());
                    model.setTipoContexto(request.getTipoContexto());
                    model.setFechaInicio(request.getFechaInicio());
                    model.setFechaFin(request.getFechaFin());
                    model.setTotalSesiones(request.getTotalSesiones());
                    model.setEstado(request.getEstado());
                    model.setFechaModificacion(LocalDateTime.now());
                    model.setUsuarioModificacion(request.getUsuarioCreacion());
                    
                    ProgramacionModel saved = programacionRepository.save(model);
                    return modelMapper.map(saved, ProgramacionResponse.class);
                })
                .orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando programación ID: {}", id);
        programacionRepository.deleteById(id);
    }

    @Override
    public List<ProgramacionResponse> buscarPorSistema(String sistemaOrigen) {
        log.info("Buscando programaciones por sistema: {}", sistemaOrigen);
        return programacionRepository.findBySistemaOrigen(sistemaOrigen)
                .stream()
                .map(model -> modelMapper.map(model, ProgramacionResponse.class))
                .toList();
    }

    @Override
    public List<ProgramacionResponse> buscarPorTipo(String tipoProgramacion) {
        log.info("Buscando programaciones por tipo: {}", tipoProgramacion);
        return programacionRepository.findByTipoProgramacion(tipoProgramacion)
                .stream()
                .map(model -> modelMapper.map(model, ProgramacionResponse.class))
                .toList();
    }

    @Override
    public List<ProgramacionResponse> buscarPorContexto(String tipoContexto, Integer contextoId) {
        log.info("Buscando programaciones por contexto: {} - {}", tipoContexto, contextoId);
        return programacionRepository.findByTipoContextoAndContextoId(tipoContexto, contextoId)
                .stream()
                .map(model -> modelMapper.map(model, ProgramacionResponse.class))
                .toList();
    }
}
