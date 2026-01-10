<<<<<<< HEAD
package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.EditorialRequest;
import tdunu2025.msbiblioteca.model.response.EditorialResponse;
=======
package TDUNU2025.Msbiblioteca.service;

import java.util.List;

import TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import TDUNU2025.Msbiblioteca.model.response.EditorialResponse;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

public interface EditorialService {

    List<EditorialResponse> listar();

    EditorialResponse obtener(Long id);

    EditorialResponse registrar (EditorialRequest request);

    EditorialResponse actualizar(Long id, EditorialRequest request);

    void eliminar(Long id);
}
