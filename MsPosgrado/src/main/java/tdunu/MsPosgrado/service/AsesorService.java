package tdunu.MsPosgrado.service;

import java.util.List;
import tdunu.MsPosgrado.model.AsesorModel;

public interface AsesorService {
    List<AsesorModel> listar();

    AsesorModel obtenerPorId(Integer id);

    AsesorModel guardar(AsesorModel asesor);

    void eliminar(Integer id);
}