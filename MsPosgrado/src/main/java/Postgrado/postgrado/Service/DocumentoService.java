package Postgrado.postgrado.Service;

import Postgrado.postgrado.Model.Documento;
import java.util.List;

public interface DocumentoService {

    Documento crear(Documento documento);

    List<Documento> listar();

    Documento obtenerPorId(Integer id);

    void eliminar(Integer id);
}

