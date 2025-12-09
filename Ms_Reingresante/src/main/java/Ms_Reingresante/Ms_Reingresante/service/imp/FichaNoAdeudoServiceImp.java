package Ms_Reingresante.Ms_Reingresante.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ms_Reingresante.Ms_Reingresante.model.entity.FichaNoAdeudoModel;
import Ms_Reingresante.Ms_Reingresante.model.entity.InformeAcademicoModel;

// Importaciones de Modelos y Repositorios

import Ms_Reingresante.Ms_Reingresante.model.request.FichaNoAdeudoRequest;
import Ms_Reingresante.Ms_Reingresante.model.request.InformeAcademicoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.FichaNoAdeudoResponse;
import Ms_Reingresante.Ms_Reingresante.model.response.InformeAcademicoResponse;
import Ms_Reingresante.Ms_Reingresante.repository.FichaNoAdeudoRepository;
import Ms_Reingresante.Ms_Reingresante.repository.InformeAcademicoRepository;
import Ms_Reingresante.Ms_Reingresante.service.FichaNoAdeudoService;


@Service
public class FichaNoAdeudoServiceImp implements FichaNoAdeudoService {

    private final FichaNoAdeudoRepository FichaNoAdeudoRepository;
    private final ModelMapper modelMapper;

    // Inyección de dependencias
    @Autowired
    public FichaNoAdeudoServiceImp(FichaNoAdeudoRepository fichaNoAdeudoRepository, ModelMapper modelMapper) {
        this.FichaNoAdeudoRepository = fichaNoAdeudoRepository;
        this.modelMapper = modelMapper;
    }

    // --- MÉTODOS CRUD BÁSICOS ---

    @Override
    public List<FichaNoAdeudoResponse> listar() {
        return FichaNoAdeudoRepository.findAll()
            .stream()
            .map(entity -> modelMapper.map(entity, FichaNoAdeudoResponse.class))
            .toList();
    }

    @Override
    public FichaNoAdeudoResponse obtenerPorId(Integer id) {
        return FichaNoAdeudoRepository.findById(id)
            .map(entity -> modelMapper.map(entity, FichaNoAdeudoResponse.class))
            // Es crucial manejar el caso de no encontrar el recurso (lanzar excepción o devolver null)
            .orElseThrow(() -> new RuntimeException("Ficha No Adeudo no encontrada con ID: " + id)); 
    }

    @Override
  public FichaNoAdeudoResponse guardar(FichaNoAdeudoRequest request) {
    // 1. Request -> Model
    FichaNoAdeudoModel model = modelMapper.map(request,FichaNoAdeudoModel.class);

    // 2. Guardar en BD
     FichaNoAdeudoModel saved = FichaNoAdeudoRepository.save(model);
    // 3. Model -> Response
   FichaNoAdeudoResponse response = modelMapper.map(saved, FichaNoAdeudoResponse.class);

    return response;
  }


   @Override
  public void eliminar(Integer id) {
    FichaNoAdeudoRepository.deleteById(id);
  }



}