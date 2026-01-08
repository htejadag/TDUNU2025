package msposgrado.Service;

import msposgrado.Model.Jurado;
import java.util.List;

public interface JuradoService {

    Jurado crear(Jurado jurado);

    List<Jurado> listar();

    Jurado obtenerPorId(Integer id);

    Jurado actualizar(Integer id, Jurado jurado);

    void eliminar(Integer id);
}
