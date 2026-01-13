package tdunu.MsPersona.service.Imp;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsPersona.model.entity.DocenteModel;
import tdunu.MsPersona.model.request.DocenteRequest;
import tdunu.MsPersona.model.response.DocenteResponse;
import tdunu.MsPersona.repository.DocenteRepository;
import tdunu.MsPersona.service.DocenteService;
import java.time.LocalDateTime;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class DocenteServiceImp implements DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DocenteResponse> listar() {
        return docenteRepository.findAll()
            .stream()
            .map(docente -> modelMapper.map(docente, DocenteResponse.class))
            .toList();
    }

    @Override
    public DocenteResponse obtenerPorId(Integer id) {
        return docenteRepository.findById(id)
            .map(docente -> modelMapper.map(docente, DocenteResponse.class))
            .orElse(null);
    }

    @Override
    public DocenteResponse guardar(DocenteRequest request) {
        log.info("Guardando docente: {}", request.getDocNombres());
        
        // Validaciones de unicidad
        if (docenteRepository.existsByDocCodigo(request.getDocCodigo())) {
            throw new RuntimeException("El código de docente ya existe");
        }
        if (docenteRepository.existsByDocDni(request.getDocDni())) {
            throw new RuntimeException("El DNI ya está registrado");
        }
        if (request.getDocEmail() != null && !request.getDocEmail().isEmpty() 
            && docenteRepository.existsByDocEmail(request.getDocEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        DocenteModel docente = modelMapper.map(request, DocenteModel.class);
        
        // Setear campos de auditoría
        docente.setFechaCreacion(LocalDateTime.now());
        docente.setUsuarioCreacion("SISTEMA");
        
        DocenteModel saved = docenteRepository.save(docente);
        log.info("Docente guardado con ID: {}", saved.getIdDocente());
        
        return modelMapper.map(saved, DocenteResponse.class);
    }

    @Override
    public DocenteResponse actualizar(Integer id, DocenteRequest request) {
        log.info("Actualizando docente ID: {}", id);
        
        DocenteModel docente = docenteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + id));

        // Validar que DNI no esté duplicado (excluyendo el actual)
        if (!docente.getDocDni().equals(request.getDocDni()) 
            && docenteRepository.existsByDocDni(request.getDocDni())) {
            throw new RuntimeException("El DNI ya está registrado en otro docente");
        }
        
        // Validar que código no esté duplicado (excluyendo el actual)
        if (!docente.getDocCodigo().equals(request.getDocCodigo()) 
            && docenteRepository.existsByDocCodigo(request.getDocCodigo())) {
            throw new RuntimeException("El código ya está registrado en otro docente");
        }
        
        // Validar que email no esté duplicado (excluyendo el actual)
        if (request.getDocEmail() != null && !request.getDocEmail().isEmpty() 
            && !docente.getDocEmail().equals(request.getDocEmail()) 
            && docenteRepository.existsByDocEmail(request.getDocEmail())) {
            throw new RuntimeException("El email ya está registrado en otro docente");
        }

        // Mapear cambios
        modelMapper.map(request, docente);
        docente.setIdDocente(id);
        
        // Actualizar campos de auditoría
        docente.setFechaModificacion(LocalDateTime.now());
        docente.setUsuarioModificacion("SISTEMA");
        
        DocenteModel actualizado = docenteRepository.save(docente);
        log.info("Docente actualizado correctamente ID: {}", id);
        
        return modelMapper.map(actualizado, DocenteResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando docente ID: {}", id);
        
        if (!docenteRepository.existsById(id)) {
            throw new RuntimeException("Docente no encontrado con ID: " + id);
        }
        
        docenteRepository.deleteById(id);
        log.info("Docente eliminado correctamente ID: {}", id);
    }
}