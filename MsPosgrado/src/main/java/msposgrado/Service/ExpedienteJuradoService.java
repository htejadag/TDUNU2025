package msposgrado.Service;

import msposgrado.Model.ExpedienteJurado;
import java.util.List;

public interface ExpedienteJuradoService {

    ExpedienteJurado crear(ExpedienteJurado ej);

    List<ExpedienteJurado> listar();

    ExpedienteJurado obtenerPorId(Integer id);

    void eliminar(Integer id);
}
