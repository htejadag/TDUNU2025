package tdunu2025.Msbiblioteca.service;

import java.util.List;

import tdunu2025.Msbiblioteca.model.request.EditorialRequest;
import tdunu2025.Msbiblioteca.model.response.EditorialResponse;

public interface EditorialService {

    List<EditorialResponse> listar();

    EditorialResponse obtener(Long id);

    EditorialResponse registrar (EditorialRequest request);

    EditorialResponse actualizar(Long id, EditorialRequest request);

    void eliminar(Long id);
}
