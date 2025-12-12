package Postgrado.postgrado.Service;

import Postgrado.postgrado.Model.Jurado;
import java.util.List;

public interface JuradoService {

    Jurado crear(Jurado jurado);

    List<Jurado> listar();

    Jurado obtenerPorId(Integer id);

    void eliminar(Integer id);
}
