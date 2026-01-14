package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.EditorialRequest;
import tdunu2025.msbiblioteca.model.response.EditorialResponse;

public interface EditorialService {

    List<EditorialResponse> listar();

    EditorialResponse obtener(Long id);

    EditorialResponse registrar (EditorialRequest request);

    EditorialResponse actualizar(Long id, EditorialRequest request);

    void eliminar(Long id);
}
