package tdunu.MsTemplate.service.Imp;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsTemplate.model.DemoModel;
import tdunu.MsTemplate.repository.DemoRepository;
import tdunu.MsTemplate.service.DemoService;

@Slf4j
@Service
public class DemoServiceImp implements DemoService {

  @Autowired
  DemoRepository demoRepository;

  @Override
  public List<DemoModel> listar() {
    return demoRepository.findAll();
  }

  @Override
  public DemoModel obtenerPorId(Integer id) {
    return demoRepository.findById(id).orElse(null);
  }

  @Override
  public DemoModel guardar(DemoModel model) {
    log.info("Iniciando guardar demoModel");
    return demoRepository.save(model);
  }

  @Override
  public void eliminar(Integer id) {
    demoRepository.deleteById(id);
  }

}