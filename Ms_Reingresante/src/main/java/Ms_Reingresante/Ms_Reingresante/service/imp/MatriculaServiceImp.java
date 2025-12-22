package Ms_Reingresante.Ms_Reingresante.service.imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Ms_Reingresante.Ms_Reingresante.model.entity.MatriculaModel;
import Ms_Reingresante.Ms_Reingresante.model.request.MatriculaRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.MatriculaResponse;
import Ms_Reingresante.Ms_Reingresante.repository.MatriculaRepository;
import Ms_Reingresante.Ms_Reingresante.service.MatriculaService;

import java.util.List;

@Service
public class MatriculaServiceImp implements MatriculaService {
    
    private final MatriculaRepository matriculaRepository;
    private final ModelMapper modelMapper;
    
    @Autowired
    public MatriculaServiceImp(MatriculaRepository matriculaRepository, ModelMapper modelMapper) {
        this.matriculaRepository = matriculaRepository;
        this.modelMapper = modelMapper;
    }
    
    @Override
    public List<MatriculaResponse> listar() {
        return matriculaRepository.findAll()
            .stream()
            .map(entity -> modelMapper.map(entity, MatriculaResponse.class))
            .toList();
    }
    
    @Override
    public MatriculaResponse obtenerPorId(Long id) {
        MatriculaModel entity = matriculaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));
        return modelMapper.map(entity, MatriculaResponse.class);
    }
    
    @Override
    public MatriculaResponse guardar(MatriculaRequest request) {
        MatriculaModel model = modelMapper.map(request, MatriculaModel.class);
        MatriculaModel saved = matriculaRepository.save(model);
        return modelMapper.map(saved, MatriculaResponse.class);
    }
    
    @Override
    public MatriculaResponse actualizar(Long id, MatriculaRequest request) {
        MatriculaModel existing = matriculaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));
        
        modelMapper.map(request, existing);
        existing.setIdMatricula(id);
        
        MatriculaModel updated = matriculaRepository.save(existing);
        return modelMapper.map(updated, MatriculaResponse.class);
    }
    
    @Override
    public void eliminar(Long id) {
        if (!matriculaRepository.existsById(id)) {
            throw new RuntimeException("Matrícula no encontrada con ID: " + id);
        }
        matriculaRepository.deleteById(id);
    }
}