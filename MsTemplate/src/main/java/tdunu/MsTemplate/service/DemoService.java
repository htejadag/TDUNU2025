package tdunu.MsTemplate.service;

import java.util.List;
import tdunu.MsTemplate.model.request.DemoRequest;
import tdunu.MsTemplate.model.response.DemoResponse;

public interface DemoService {

  List<DemoResponse> listar();

  DemoResponse obtenerPorId(Integer id);

  DemoResponse guardar(DemoRequest producto);

  void eliminar(Integer id);

}