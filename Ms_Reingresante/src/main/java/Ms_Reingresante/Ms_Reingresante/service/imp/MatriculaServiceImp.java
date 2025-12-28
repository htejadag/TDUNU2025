package Ms_Reingresante.Ms_Reingresante.service.imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Ms_Reingresante.Ms_Reingresante.model.entity.MatriculaModel;
import Ms_Reingresante.Ms_Reingresante.model.request.MatriculaRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.MatriculaResponse;
import Ms_Reingresante.Ms_Reingresante.repository.MatriculaRepository;
import Ms_Reingresante.Ms_Reingresante.service.MatriculaService;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class MatriculaServiceImp implements MatriculaService {
    
    @Autowired
    private MatriculaRepository MatriculaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MatriculaResponse> listarMatricula() {
        return MatriculaRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, MatriculaResponse.class))
            .toList();
    }



  @Override
  public MatriculaResponse obtenerPorIdMatricula(Integer id) {
    return MatriculaRepository.findById(id)
        .map(model -> modelMapper.map(model, MatriculaResponse.class))
        .orElse(null);
  }

  @Override
  public MatriculaResponse guardarMatricula(MatriculaRequest request) {
    // 1. Request -> Model
    MatriculaModel model = modelMapper.map(request,MatriculaModel.class);

    // 2. Guardar en BD
    MatriculaModel saved = MatriculaRepository.save(model);

    // 3. Model -> Response
   MatriculaResponse response = modelMapper.map(saved, MatriculaResponse.class);

    return response;
  }

  @Override
  public void eliminarMatricula(Integer id) {
    MatriculaRepository.deleteById(id);
  }

   @Override
    @Transactional
    public MatriculaModel update(MatriculaModel accountModel) {
        return MatriculaRepository.save(accountModel);
    }
 
    @Override
    public MatriculaModel findById(Integer id) {
        return MatriculaRepository.findById(id).orElse(null);
    } 


}