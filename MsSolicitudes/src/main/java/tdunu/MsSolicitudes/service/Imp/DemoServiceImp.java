package tdunu.MsSolicitudes.service.Imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsSolicitudes.model.entity.SolicitudesModel;
import tdunu.MsSolicitudes.model.request.DemoRequest;
import tdunu.MsSolicitudes.model.response.DemoResponse;
import tdunu.MsSolicitudes.repository.DemoRepository;
import tdunu.MsSolicitudes.service.DemoService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DemoServiceImp implements DemoService {

   private final DemoRepository repository;

    @Override
    @Transactional
    public DemoResponse crear(DemoRequest request) {
        SolicitudesModel entity = new SolicitudesModel();
        
        entity.setIdEstudiante(request.getIdEstudiante());
        entity.setProcCodigo(request.getProcCodigo());
        entity.setProcFechaInicio(request.getProcFechaInicio());
        entity.setProcEstado(request.getProcEstado() != null ? request.getProcEstado() : "INICIADO");
        entity.setProcFaseActual(request.getProcFaseActual() != null ? request.getProcFaseActual() : "PAGO_FICHA");
        entity.setProcObservaciones(request.getProcObservaciones());
        entity.setFechaCreacion(LocalDateTime.now());
        entity.setUsuarioCreacion(request.getUsuarioCreacion());
        
        SolicitudesModel saved = repository.save(entity);
        return DemoResponse.fromEntity(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DemoResponse obtenerPorId(Integer id) {
        SolicitudesModel entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proceso de reingreso no encontrado con ID: " + id));
        return DemoResponse.fromEntity(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DemoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(DemoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DemoResponse actualizar(Integer id, DemoRequest request) {
        SolicitudesModel entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proceso de reingreso no encontrado con ID: " + id));
        if (request.getProcEstado() != null) {
            entity.setProcEstado(request.getProcEstado());
        }
        if (request.getProcFaseActual() != null) {
            entity.setProcFaseActual(request.getProcFaseActual());
        }
        if (request.getProcObservaciones() != null) {
            entity.setProcObservaciones(request.getProcObservaciones());
        }

        entity.setFechaModificacion(LocalDateTime.now());
        entity.setUsuarioModificacion(request.getUsuarioCreacion());
        
        SolicitudesModel updated = repository.save(entity);
        return DemoResponse.fromEntity(updated);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Proceso de reingreso no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DemoResponse> listarPorEstudiante(Integer idEstudiante) {
        return repository.findByIdEstudiante(idEstudiante).stream()
                .map(DemoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DemoResponse> listarPorEstado(String estado) {
        return repository.findByProcEstado(estado).stream()
                .map(DemoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DemoResponse> listarPorFase(String fase) {
        return repository.findByProcFaseActual(fase).stream()
                .map(DemoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DemoResponse obtenerPorCodigo(String codigo) {
        SolicitudesModel entity = repository.findByProcCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Proceso de reingreso no encontrado con código: " + codigo));
        return DemoResponse.fromEntity(entity);
    }

}