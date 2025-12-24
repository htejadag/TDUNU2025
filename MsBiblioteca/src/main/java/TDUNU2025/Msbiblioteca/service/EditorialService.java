package TDUNU2025.Msbiblioteca.service;

import java.util.List;

import TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import TDUNU2025.Msbiblioteca.model.response.EditorialResponse;

public interface EditorialService {

    List<EditorialResponse> listar();

    EditorialResponse obtenerPorId(Long id);

    EditorialResponse guardar(EditorialRequest editorial);

    EditorialResponse actualizar(Long id, EditorialRequest request);

    void eliminar(Long id);

}
