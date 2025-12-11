package tdunu.MsPosgrado.service;

import java.util.List;
import tdunu.MsPosgrado.model.JuradoModel;

public interface JuradoService {

    JuradoModel guardar(JuradoModel jurado);

    List<JuradoModel> listar();

    JuradoModel obtenerPorId(Integer id);

    void eliminar(Integer id);
}
