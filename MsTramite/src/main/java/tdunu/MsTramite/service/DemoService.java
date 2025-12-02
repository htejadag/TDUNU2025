package tdunu.MsTramite.service;

import java.util.List;

import tdunu.MsTramite.model.request.DemoRequest;
import tdunu.MsTramite.model.response.DemoResponse;

public interface DemoService {

  List<DemoResponse> listar();

  DemoResponse obtenerPorId(Integer id);

  DemoResponse guardar(DemoRequest producto);

  void eliminar(Integer id);

}