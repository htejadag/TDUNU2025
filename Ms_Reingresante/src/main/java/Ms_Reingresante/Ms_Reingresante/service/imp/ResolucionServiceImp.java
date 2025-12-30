package Ms_Reingresante.Ms_Reingresante.service.imp;


import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ms_Reingresante.Ms_Reingresante.model.entity.ResolucionModel;
import Ms_Reingresante.Ms_Reingresante.model.request.ResolucionRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.ResolucionResponse;
import Ms_Reingresante.Ms_Reingresante.repository.ResolucionRepository;
import Ms_Reingresante.Ms_Reingresante.service.ResolucionService;




@Service
public class ResolucionServiceImp implements ResolucionService {

    @Autowired
    private ResolucionRepository resolucionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ResolucionResponse> listarResolucion() {
        return resolucionRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, ResolucionResponse.class))
            .toList();
    }



     @Override
  public ResolucionResponse obtenerPorIdResolucion(Integer id) {
    return resolucionRepository.findById(id)
        .map(model -> modelMapper.map(model, ResolucionResponse.class))
        .orElse(null);
  }

  @Override
  public ResolucionResponse guardarResolucion(ResolucionRequest request) {
    // 1. Request -> Model
    ResolucionModel model = modelMapper.map(request,ResolucionModel.class);

    // 2. Guardar en BD
    ResolucionModel saved = resolucionRepository.save(model);

    // 3. Model -> Response
   ResolucionResponse response = modelMapper.map(saved, ResolucionResponse.class);

    return response;
  }

  @Override
  public void eliminarResolucion(Integer id) {
    resolucionRepository.deleteById(id);
  }

}
