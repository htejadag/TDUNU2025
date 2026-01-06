package Postgrado.postgrado.Service;

import Postgrado.postgrado.Model.Asesor;
import java.util.List;

public interface AsesorService {

    Asesor crear(Asesor asesor);

    Asesor actualizar(Integer id,Asesor asesor);

    List<Asesor> listar();

    Asesor obtenerPorId(Integer id);

    void eliminar(Integer id);
}
