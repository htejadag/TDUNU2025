package Ms_Reingresante.Ms_Reingresante.service.imp;


import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ms_Reingresante.Ms_Reingresante.model.entity.resolucionModel;
import Ms_Reingresante.Ms_Reingresante.model.request.resolucionRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.resolucionResponse;
import Ms_Reingresante.Ms_Reingresante.repository.ResolucionRepository;
import Ms_Reingresante.Ms_Reingresante.service.ResolucionService;




@Service
public class ResolucionServiceImp implements ResolucionService {

    @Autowired
    private ResolucionRepository resolucionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<resolucionResponse> listarResolucion() {
        return resolucionRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, resolucionResponse.class))
            .toList();
    }



     @Override
  public resolucionResponse obtenerPorIdResolucion(Integer id) {
    return resolucionRepository.findById(id)
        .map(model -> modelMapper.map(model, resolucionResponse.class))
        .orElse(null);
  }

  @Override
  public resolucionResponse guardarResolucion(resolucionRequest request) {
    // 1. Request -> Model
    resolucionModel model = modelMapper.map(request,resolucionModel.class);

    // 2. Guardar en BD
    resolucionModel saved = resolucionRepository.save(model);

    // 3. Model -> Response
   resolucionResponse response = modelMapper.map(saved, resolucionResponse.class);

    return response;
  }

  @Override
  public void eliminarResolucion(Integer id) {
    resolucionRepository.deleteById(id);
  }

}
