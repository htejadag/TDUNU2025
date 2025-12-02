package tdunu.MsTramite.service.Imp;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import tdunu.MsTramite.model.entity.DemoModel;
import tdunu.MsTramite.model.request.DemoRequest;
import tdunu.MsTramite.model.response.DemoResponse;
import tdunu.MsTramite.repository.DemoRepository;
import tdunu.MsTramite.service.DemoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DemoServiceImp implements DemoService {

  @Autowired
  DemoRepository demoRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public List<DemoResponse> listar() {
    return demoRepository.findAll()
        .stream()
        .map(model -> modelMapper.map(model, DemoResponse.class))
        .toList();
  }

  @Override
  public DemoResponse obtenerPorId(Integer id) {
    return demoRepository.findById(id)
        .map(model -> modelMapper.map(model, DemoResponse.class))
        .orElse(null);
  }

  @Override
  public DemoResponse guardar(DemoRequest request) {
    // 1. Request -> Model
    DemoModel model = modelMapper.map(request, DemoModel.class);

    // 2. Guardar en BD
    DemoModel saved = demoRepository.save(model);

    // 3. Model -> Response
    DemoResponse response = modelMapper.map(saved, DemoResponse.class);

    return response;
  }

  @Override
  public void eliminar(Integer id) {
    demoRepository.deleteById(id);
  }

}