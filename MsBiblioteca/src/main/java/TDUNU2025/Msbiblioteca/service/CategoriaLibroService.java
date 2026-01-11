<<<<<<< HEAD
package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.CategoriaLibroRequest;
import tdunu2025.msbiblioteca.model.response.CategoriaLibroResponse;
=======
package TDUNU2025.Msbiblioteca.service;

import java.util.List;

import TDUNU2025.Msbiblioteca.model.request.CategoriaLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.CategoriaLibroResponse;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

public interface CategoriaLibroService {

    CategoriaLibroResponse registrar (CategoriaLibroRequest request);

    CategoriaLibroResponse obtener (Long idCategoria);

    List<CategoriaLibroResponse> listar();

    CategoriaLibroResponse actualizar(Long idCategoria, CategoriaLibroRequest request);

    void eliminar(Long idCategoria);
}