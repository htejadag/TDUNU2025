package Ms_Reingresante.Ms_Reingresante.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Importaciones de Modelos y Repositorios
import Ms_Reingresante.Ms_Reingresante.model.entity.FichaNoAdeudoEntity;
import Ms_Reingresante.Ms_Reingresante.model.request.FichaNoAdeudoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.FichaNoAdeudoResponse;
import Ms_Reingresante.Ms_Reingresante.repository.FichaNoAdeudoRepository;
import Ms_Reingresante.Ms_Reingresante.service.FichaNoAdeudoService;


@Service
public class FichaNoAdeudoServiceImpl implements FichaNoAdeudoService {

    private final FichaNoAdeudoRepository fichaNoAdeudoRepository;
    private final ModelMapper modelMapper;

    // Inyección de dependencias
    @Autowired
    public FichaNoAdeudoServiceImpl(FichaNoAdeudoRepository fichaNoAdeudoRepository, ModelMapper modelMapper) {
        this.fichaNoAdeudoRepository = fichaNoAdeudoRepository;
        this.modelMapper = modelMapper;
    }

    // --- MÉTODOS CRUD BÁSICOS ---

    @Override
    public List<FichaNoAdeudoResponse> listar() {
        return fichaNoAdeudoRepository.findAll()
            .stream()
            .map(entity -> modelMapper.map(entity, FichaNoAdeudoResponse.class))
            .toList();
    }

    @Override
    public FichaNoAdeudoResponse obtenerPorId(Long id) {
        return fichaNoAdeudoRepository.findById(id)
            .map(entity -> modelMapper.map(entity, FichaNoAdeudoResponse.class))
            // Es crucial manejar el caso de no encontrar el recurso (lanzar excepción o devolver null)
            .orElseThrow(() -> new RuntimeException("Ficha No Adeudo no encontrada con ID: " + id)); 
    }

    // --- LÓGICA DE NEGOCIO ---

    @Override
    public FichaNoAdeudoResponse generarFicha(FichaNoAdeudoRequest request) {
        
        // 1. Request -> Entity
        FichaNoAdeudoEntity nuevaFicha = modelMapper.map(request, FichaNoAdeudoEntity.class);

        // 2. Asignación de datos automáticos (Lógica de Negocio)
        // Lógica: Generar la fecha de emisión y un número correlativo
        nuevaFicha.setFechaEmision(LocalDate.now());
        nuevaFicha.setFichaNumero("FNA-" + nuevaFicha.getIdProceso() + "-" + System.currentTimeMillis()); 
        // Lógica de Auditoría
        nuevaFicha.setFechaCreacion(java.time.LocalDateTime.now());
        nuevaFicha.setUsuarioCreacion(request.usuarioCreacion); 

        // 3. Guardar en BD
        FichaNoAdeudoEntity savedEntity = fichaNoAdeudoRepository.save(nuevaFicha);

        // 4. Entity -> Response
        return modelMapper.map(savedEntity, FichaNoAdeudoResponse.class);
    }

    @Override
    public FichaNoAdeudoResponse aprobarFicha(Long id) {
        FichaNoAdeudoEntity entity = fichaNoAdeudoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ficha No Adeudo no encontrada para aprobar."));

        // **Lógica de Aprobación:**
        // Aquí se actualizarían los campos de estado y auditoría.
        entity.setUsuarioModificacion("Usuario_Aprobador"); 
        entity.setFechaModificacion(java.time.LocalDateTime.now());
        
        FichaNoAdeudoEntity updatedEntity = fichaNoAdeudoRepository.save(entity);

        return modelMapper.map(updatedEntity, FichaNoAdeudoResponse.class);
    }
}