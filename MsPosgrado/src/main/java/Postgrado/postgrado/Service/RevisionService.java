package Postgrado.postgrado.Service;

import Postgrado.postgrado.Model.Revision;
import java.util.List;

public interface RevisionService {

    Revision crear(Revision revision);

    List<Revision> listar();

    Revision obtenerPorId(Integer id);

    void eliminar(Integer id);
}
