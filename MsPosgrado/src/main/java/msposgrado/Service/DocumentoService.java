package msposgrado.Service;

import msposgrado.Model.Documento;
import java.util.List;

public interface DocumentoService {

    Documento crear(Documento documento);

    List<Documento> listar();

    Documento obtenerPorId(Integer id);

    void eliminar(Integer id);
}

