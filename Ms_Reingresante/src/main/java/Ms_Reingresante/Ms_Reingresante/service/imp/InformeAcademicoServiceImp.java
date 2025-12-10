package Ms_Reingresante.Ms_Reingresante.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Importaciones de Modelos y Repositorios
import Ms_Reingresante.Ms_Reingresante.model.entity.InformeAcademicoEntity;
import Ms_Reingresante.Ms_Reingresante.model.request.InformeAcademicoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.InformeAcademicoResponse;
import Ms_Reingresante.Ms_Reingresante.repository.InformeAcademicoRepository;
import Ms_Reingresante.Ms_Reingresante.service.InformeAcademicoService;


@Service
public class InformeAcademicoServiceImpl implements InformeAcademicoService {

    private final InformeAcademicoRepository informeAcademicoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public InformeAcademicoServiceImpl(InformeAcademicoRepository informeAcademicoRepository, ModelMapper modelMapper) {
        this.informeAcademicoRepository = informeAcademicoRepository;
        this.modelMapper = modelMapper;
    }

    // --- MÉTODOS CRUD BÁSICOS ---
    
    @Override
    public List<InformeAcademicoResponse> listar() {
        return informeAcademicoRepository.findAll()
            .stream()
            .map(entity -> modelMapper.map(entity, InformeAcademicoResponse.class))
            .toList();
    }

    @Override
    public InformeAcademicoResponse obtenerPorId(Long id) {
        return informeAcademicoRepository.findById(id)
            .map(entity -> modelMapper.map(entity, InformeAcademicoResponse.class))
            .orElseThrow(() -> new RuntimeException("Informe Académico no encontrado con ID: " + id));
    }

    // --- LÓGICA DE NEGOCIO ---

    @Override
    public InformeAcademicoResponse generarInforme(InformeAcademicoRequest request) {
        
        // **Lógica de Generación de Informe (Simulación):**
        // Lógica real: Consultar otros servicios para obtener el historial académico, 
        // promedio ponderado, etc., y luego asignar esos valores al Entity.

        // 1. Request -> Entity
        InformeAcademicoEntity nuevoInforme = modelMapper.map(request, InformeAcademicoEntity.class);
        
        // 2. Asignación de datos automáticos (Lógica de Negocio)
        nuevoInforme.setFechaEmision(LocalDate.now());
        // Lógica para asignar el número correlativo.
        nuevoInforme.setInfNumero("IAC-" + nuevoInforme.getIdProceso() + "-" + LocalDate.now().getYear());
        // Lógica de Auditoría
        nuevoInforme.setFechaCreacion(java.time.LocalDateTime.now());
        nuevoInforme.setUsuarioCreacion(request.usuarioCreacion); 
        
        // 3. Guardar en BD
        InformeAcademicoEntity savedEntity = informeAcademicoRepository.save(nuevoInforme);

        // 4. Entity -> Response
        return modelMapper.map(savedEntity, InformeAcademicoResponse.class);
    }
}