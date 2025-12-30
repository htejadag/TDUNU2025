package TDUNU2025.Msbiblioteca.service;

<<<<<<< HEAD
import java.util.List;

import TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import TDUNU2025.Msbiblioteca.model.response.EditorialResponse;

public interface EditorialService {

    List<EditorialResponse> listar();

    EditorialResponse obtenerPorId(Long id);

    EditorialResponse guardar(EditorialRequest editorial);
=======
import TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import TDUNU2025.Msbiblioteca.model.response.EditorialResponse;

import java.util.List;

public interface EditorialService {

    EditorialResponse guardar(EditorialRequest request);

    List<EditorialResponse> listar();

    EditorialResponse buscarPorId(Long id);
>>>>>>> origin/origin/jordinTrujillo

    EditorialResponse actualizar(Long id, EditorialRequest request);

    void eliminar(Long id);
<<<<<<< HEAD

=======
>>>>>>> origin/origin/jordinTrujillo
}
