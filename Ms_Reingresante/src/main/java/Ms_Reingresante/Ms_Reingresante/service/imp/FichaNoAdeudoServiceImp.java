package Ms_Reingresante.Ms_Reingresante.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ms_Reingresante.Ms_Reingresante.model.entity.FichaNoAdeudoModel;
import Ms_Reingresante.Ms_Reingresante.model.entity.resolucionModel;

// Importaciones de Modelos y Repositorios

import Ms_Reingresante.Ms_Reingresante.model.request.FichaNoAdeudoRequest;
import Ms_Reingresante.Ms_Reingresante.model.request.resolucionRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.FichaNoAdeudoResponse;
import Ms_Reingresante.Ms_Reingresante.model.response.resolucionResponse;
import Ms_Reingresante.Ms_Reingresante.repository.FichaNoAdeudoRepository;
import Ms_Reingresante.Ms_Reingresante.service.FichaNoAdeudoService;


@Service
public class FichaNoAdeudoServiceImp implements FichaNoAdeudoService {
    
    @Autowired
    private FichaNoAdeudoRepository FichaNoAdeudoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FichaNoAdeudoResponse> listarFichaNoAdeudo() {
        return FichaNoAdeudoRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model,  FichaNoAdeudoResponse.class))
            .toList();
    }


    // --- MÉTODOS CRUD BÁSICOS ---

    
    @Override
    public FichaNoAdeudoResponse obtenerPorIdFichaNoAdeudo(Integer id) {
        return FichaNoAdeudoRepository.findById(id)
            .map(entity -> modelMapper.map(entity, FichaNoAdeudoResponse.class))
            // Es crucial manejar el caso de no encontrar el recurso (lanzar excepción o devolver null)
            .orElseThrow(() -> new RuntimeException("Ficha No Adeudo no encontrada con ID: " + id)); 
    }

  @Override
  public FichaNoAdeudoResponse guardarFichaNoAdeudo(FichaNoAdeudoRequest request) {
    // 1. Request -> Model
    FichaNoAdeudoModel model = modelMapper.map(request, FichaNoAdeudoModel.class);

    // 2. Guardar en BD
    FichaNoAdeudoModel saved = FichaNoAdeudoRepository.save(model);

    // 3. Model -> Response
   FichaNoAdeudoResponse response = modelMapper.map(saved,  FichaNoAdeudoResponse.class);

    return response;
  }


   @Override
  public void eliminarFichaNoAdeudo(Integer id) {
    FichaNoAdeudoRepository.deleteById(id);
  }



}