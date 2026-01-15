package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import TDUNU2025.Msbiblioteca.model.response.EditorialResponse;

import java.util.List;

public interface EditorialService {

    EditorialResponse guardar(EditorialRequest request);

    List<EditorialResponse> listar();

    EditorialResponse buscarPorId(Long id);

    EditorialResponse actualizar(Long id, EditorialRequest request);

    void eliminar(Long id);
}
