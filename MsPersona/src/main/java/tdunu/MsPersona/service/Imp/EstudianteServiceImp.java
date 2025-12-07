package tdunu.MsPersona.service.Imp;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsPersona.model.entity.EstudianteModel;
import tdunu.MsPersona.model.request.EstudianteRequest;
import tdunu.MsPersona.model.response.EstudianteResponse;
import tdunu.MsPersona.repository.EstudianteRepository;
import tdunu.MsPersona.service.EstudianteService;

@Slf4j
@Service
public class EstudianteServiceImp implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EstudianteResponse> listar() {
        return estudianteRepository.findAll()
            .stream()
            .map(estudiante -> modelMapper.map(estudiante, EstudianteResponse.class))
            .toList();
    }

    @Override
    public EstudianteResponse obtenerPorId(Integer id) {
        return estudianteRepository.findById(id)
            .map(estudiante -> modelMapper.map(estudiante, EstudianteResponse.class))
            .orElse(null);
    }

    @Override
    public EstudianteResponse guardar(EstudianteRequest request) {
        if (estudianteRepository.existsByEstCodigo(request.getEstCodigo())) {
            throw new RuntimeException("El código de estudiante ya existe");
        }
        if (estudianteRepository.existsByEstDni(request.getEstDni())) {
            throw new RuntimeException("El DNI ya está registrado");
        }

        EstudianteModel estudiante = modelMapper.map(request, EstudianteModel.class);
        EstudianteModel saved = estudianteRepository.save(estudiante);
        return modelMapper.map(saved, EstudianteResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        estudianteRepository.deleteById(id);
    }
}