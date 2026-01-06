package Postgrado.postgrado.Service;

import Postgrado.postgrado.Model.Tesis;
import java.util.List;

public interface TesisService {

    Tesis crear(Tesis tesis);

    List<Tesis> listar();

    Tesis obtenerPorId(Integer id);

    void eliminar(Integer id);
}
