package msposgrado.Service;

import msposgrado.Model.Revision;
import java.util.List;

public interface RevisionService {

    Revision crear(Revision revision);

    List<Revision> listar();

    Revision obtenerPorId(Integer id);

    void eliminar(Integer id);
}
