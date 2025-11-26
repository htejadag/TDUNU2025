package tdunu.MsSolicitudes.service;

import java.util.List;
import tdunu.MsSolicitudes.model.request.DemoRequest;
import tdunu.MsSolicitudes.model.response.DemoResponse;

public interface DemoService {

  List<DemoResponse> listar();

  DemoResponse obtenerPorId(Integer id);

  DemoResponse guardar(DemoRequest producto);

  void eliminar(Integer id);

}