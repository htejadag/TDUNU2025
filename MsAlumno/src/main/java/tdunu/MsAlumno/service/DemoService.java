package tdunu.MsAlumno.service;

import java.util.List;
import tdunu.MsAlumno.model.request.DemoRequest;
import tdunu.MsAlumno.model.response.DemoResponse;

public interface DemoService {

  List<DemoResponse> listar();

  DemoResponse obtenerPorId(Integer id);

  DemoResponse guardar(DemoRequest producto);

  void eliminar(Integer id);

}