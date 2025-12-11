package tdunu.MsPosgrado.service;

import java.util.List;
import tdunu.MsPosgrado.model.ExpedienteModel;

public interface ExpedienteService {

    List<ExpedienteModel> listar();

    ExpedienteModel obtenerPorId(Integer id);

    ExpedienteModel guardar(ExpedienteModel asesor);

    void eliminar(Integer id);
}