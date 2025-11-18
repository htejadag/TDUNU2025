package tdunu.MsTemplate.service;

import java.util.List;
import tdunu.MsTemplate.model.DemoModel;

public interface DemoService {

  List<DemoModel> listar();

  DemoModel obtenerPorId(Integer id);

  DemoModel guardar(DemoModel producto);

  void eliminar(Integer id);

}