package Postgrado.postgrado.Service;

import Postgrado.postgrado.Model.Expediente;
import java.util.List;

public interface ExpedienteService {

    Expediente crear(Expediente expediente);

    List<Expediente> listar();

    Expediente obtenerPorId(Integer id);

    void eliminar(Integer id);
}
